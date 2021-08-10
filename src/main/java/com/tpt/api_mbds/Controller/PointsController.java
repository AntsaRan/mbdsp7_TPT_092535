package com.tpt.api_mbds.Controller;

import com.tpt.api_mbds.model.Points;
import com.tpt.api_mbds.repository.PointsRepository;
import com.tpt.api_mbds.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PointsController {

    @Autowired
    PointsRepository pointsRepository;

    @GetMapping("/points")
    public ResponseEntity<List<Points>> getAllPoints(@RequestParam(required = false) String adresse){
        try {
            List<Points> points = new ArrayList<Points>();

            if (adresse == null)
                pointsRepository.findAll().forEach(points::add);
            else
                pointsRepository.findByAdresseContaining(adresse).forEach(points::add);


            if (points.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(points, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/points/getAll")
    public ResponseEntity<Response<Points>> getAll(@RequestParam(required = false) String adresse){
        try {
            List<Points> points = new ArrayList<Points>();
            Response<Points> retour=new Response<>();
            if (adresse == null)
                pointsRepository.findAll().forEach(points::add);
            else
                pointsRepository.findByAdresseContaining(adresse).forEach(points::add);


            if (points.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            retour.setPage(1);
            retour.setResults(points);
            return new ResponseEntity<>(retour, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/points/{id}")
    public ResponseEntity<Points> getPointById(@PathVariable("id") String id) {
        Optional<Points> pointsData = pointsRepository.findById(id);

        if (pointsData.isPresent()) {
            return new ResponseEntity<>(pointsData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping(path = "/points")
    public ResponseEntity<Points> createPoint(@RequestBody Points points) {
        try {
            System.out.println("Makato izy" + points.getAdresse());
            //header="application/json";
            Points _points = pointsRepository.save(new Points(points.getAdresse(), points.getLat(),points.getLng()));

            return new ResponseEntity<>(_points, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/points/{id}")
    public ResponseEntity<Points> updatePoint(@PathVariable("id") String id,@RequestBody Points points) {
        System.out.println(" Makato am PUT id : "+id);
        Optional<Points> pointsData = pointsRepository.findById(id);

        if (pointsData.isPresent()) {
            Points _tutorial = pointsData.get();
            _tutorial.setAdresse(points.getAdresse());
            _tutorial.setLat(points.getLat());
            _tutorial.setLng(points.getLng());
            System.out.println(" Point updated id : "+id);
            return new ResponseEntity<>(pointsRepository.save(_tutorial), HttpStatus.OK);
        } else {
            System.out.println("no point found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/points/{id}")
    public ResponseEntity<HttpStatus> deletePoint(@PathVariable("id")String id) {
        try {
            pointsRepository.deleteById(id);
            System.out.println(" Points deleted id : "+id);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/points")
    public ResponseEntity<HttpStatus> deleteAllPoints() {
        try {
            pointsRepository.deleteAll();
            System.out.println("All points deleted");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.tpt.api_mbds.Controller;


import com.tpt.api_mbds.model.Match;
import com.tpt.api_mbds.model.Regle;
import com.tpt.api_mbds.repository.RegleRepository;
import com.tpt.api_mbds.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RegleController {

    @Autowired
    RegleRepository regleRepository;

    @GetMapping("/regles")
    public ResponseEntity<List<Regle>> getAllRegle(@RequestParam(required = false) String titre){
        try {
            List<Regle> regles = new ArrayList<Regle>();

            if (titre == null)
                regleRepository.findAll().forEach(regles::add);
            else
                regleRepository.findReglesByTitreContaining(titre).forEach(regles::add);


            if (regles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(regles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/regle/{id}")
    public ResponseEntity<Regle> getRegleById(@PathVariable("id") String id) {
        try {
            Optional<Regle> regleData = regleRepository.findById(id);

            if (regleData.isPresent()) {
                return new ResponseEntity<>(regleData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //MOBILE
    @GetMapping("/regles/getAll")
    public ResponseEntity<Response<Regle>> getAllRegleResult(@RequestParam(required = false) String titre){
        try {
            List<Regle> regles = new ArrayList<Regle>();
            Response<Regle> resultat=new Response<>();
            if (titre == null)
                regleRepository.findAll().forEach(regles::add);
            else
                regleRepository.findReglesByTitreContaining(titre).forEach(regles::add);


            if (regles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            resultat.setPage(1);
            resultat.setResults(regles);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/regles/{id}")
    public ResponseEntity<Response<Regle>> getRegleByIdResult(@PathVariable("id") String id) {
        try {
            Optional<Regle> regleData = regleRepository.findById(id);
            Response<Regle> resultat=new Response<>();
            List<Regle> regleList=new ArrayList<>();
            if (regleData.isPresent()) {
                regleList.add(regleData.get());
                resultat.setResults(regleList);
                resultat.setPage(1);
                return new ResponseEntity<>(resultat, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

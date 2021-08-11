package com.tpt.api_mbds.Controller;

import com.tpt.api_mbds.model.Jeton;
import com.tpt.api_mbds.repository.JetonRepository;
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
public class JetonController {
    @Autowired
    JetonRepository jetonRepository;
    public JetonController() {
    }


    @GetMapping("/jetons")
    public ResponseEntity<List<Jeton>> getAllJeton(){
        try {
            List<Jeton> jeton = new ArrayList<Jeton>();
            jetonRepository.findAll().forEach(jeton::add);
            if (jeton.isEmpty()) {
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
            }
            System.out.println("ILAY PRIX JET1 "+jeton.get(0).getPrix());
            return new ResponseEntity<>(jeton, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Jeton getAllJetonNotAPI(){
        try {
            String id="60fb06e240a266419201e50a";
            Optional<Jeton> jeton = Optional.ofNullable(jetonRepository.findAllById(id));
            if (jeton.isEmpty()) {
                return null;
            }
            System.out.println("ILAY PRIX JET2 "+jeton.get().getPrix());
            return jeton.get();
        } catch (Exception e) {
            throw e;
        }
    }


    @PutMapping("/jeton/{id}")
    public ResponseEntity<Jeton> updateJeton(@PathVariable("id") String id,@RequestBody Jeton jeton) {
        System.out.println(" Makato am PUT id : "+id);
        Optional<Jeton> jetonData = jetonRepository.findById(id);

        if (jetonData.isPresent()) {
            Jeton _tutorial = jetonData.get();
            _tutorial.setPrix(jeton.getPrix());
            System.out.println(" Jeton updated id : "+id);
            return new ResponseEntity<>(jetonRepository.save(_tutorial), HttpStatus.OK);
        } else {
            System.out.println("no Jeton to updated found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}

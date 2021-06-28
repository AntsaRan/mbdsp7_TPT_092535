package com.tpt.api_mbds.Controller;


import com.tpt.api_mbds.model.Match;
import com.tpt.api_mbds.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MatchController {
    @Autowired
    MatchRepository equipeRepository;

    @GetMapping("/matches")
    public ResponseEntity<List<Match>> getAllEquipes(@RequestParam(required = false) String etat){
        try {
            List<Match> matchs = new ArrayList<Match>();

            if (etat == null)
                equipeRepository.findAll().forEach(matchs::add);
            else
                equipeRepository.findMatchByEtatContaining(etat).forEach(matchs::add);


            if (matchs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(matchs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

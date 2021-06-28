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
    MatchRepository matchRepository;

    @GetMapping("/matches")
    public ResponseEntity<List<Match>> getAllEquipes(@RequestParam(required = false) String etat){
        try {
            List<Match> matchs = new ArrayList<Match>();

            if (etat == null)
                matchRepository.findAll().forEach(matchs::add);
            else
                matchRepository.findMatchByEtatContaining(etat).forEach(matchs::add);


            if (matchs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(matchs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/match")
    public ResponseEntity<Match> createEquipe(@RequestBody Match match) {
        try {
            System.out.println("Makato izy" + match.getIdEquipe1());
            //header="application/json";
            Match _match = matchRepository.save(new Match(match.getIdEquipe1(), match.getIdEquipe2(),match.getDate(),match.getLieu(),match.getEtat(),match.getScoreEquipe1(),match.getScoreEquipe2()));

            return new ResponseEntity<>(_match, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

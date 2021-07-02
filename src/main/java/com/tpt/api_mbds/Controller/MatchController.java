package com.tpt.api_mbds.Controller;


import com.tpt.api_mbds.model.Equipe;
import com.tpt.api_mbds.model.Match;
import com.tpt.api_mbds.repository.EquipeRepository;
import com.tpt.api_mbds.repository.MatchRepository;
import com.tpt.api_mbds.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MatchController {
    @Autowired
    MatchRepository matchRepository;
    @Autowired
    EquipeRepository equipeRepository;

    @GetMapping("/matches")
    public ResponseEntity<List<Match>> getAllMatches(@RequestParam(required = false) String etat){
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
    @GetMapping("/matches/getAll")
    public ResponseEntity<Response<Match>> getAll(@RequestParam(required = false) String etat){
        try {
            List<Match> matchs = new ArrayList<Match>();
            Response<Match> retour=new Response<>();
            if (etat == null)
                matchRepository.findAll().forEach(matchs::add);
            else
                matchRepository.findMatchByEtatContaining(etat).forEach(matchs::add);


            if (matchs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            retour.setPage(1);
            retour.setResults(matchs);
            return new ResponseEntity<>(retour, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/match/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable("id") String id) {
        Optional<Match> matchData = matchRepository.findById(id);

        if (matchData.isPresent()) {
            return new ResponseEntity<>(matchData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping(path = "/match")
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        try {
            System.out.println("ID EQUIPE1 voalohany "+match.getEquipe1().getId());
            Optional<Equipe> equipe1Data = equipeRepository.findById(match.getEquipe1().getId());
            Optional<Equipe> equipe2Data = equipeRepository.findById(match.getEquipe2().getId());
            //header="application/json";
            if (equipe1Data.isPresent() && equipe2Data.isPresent()){
                Match _match = matchRepository.save(new Match(equipe1Data.get(),equipe2Data.get(),match.getDate(),match.getLieu(),match.getEtat(),match.getScoreEquipe1(),match.getScoreEquipe2()));
                return new ResponseEntity<>(_match, HttpStatus.CREATED);
            }
            else { return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/match/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable("id") String id,@RequestBody Match match) {
        System.out.println(" Makato am PUT id : "+id);
        Optional<Match> matchData = matchRepository.findById(id);

        if (matchData.isPresent()) {
            Match _match = matchData.get();
            _match.setEquipe1(match.getEquipe1());
            _match.setEquipe2(match.getEquipe2());
            _match.setDate(match.getDate());
            _match.setEtat(match.getEtat());
            _match.setLieu(match.getLieu());
            _match.setScoreEquipe1(match.getScoreEquipe1());
            _match.setScoreEquipe2(match.getScoreEquipe2());
            System.out.println(" Team updated id : "+id);
            return new ResponseEntity<>(matchRepository.save(_match), HttpStatus.OK);
        } else {
            System.out.println("no match found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/match/{id}")
    public ResponseEntity<HttpStatus> deleteMatch(@PathVariable("id")String id) {
        try {
            matchRepository.deleteById(id);
            System.out.println(" Match deleted id : "+id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

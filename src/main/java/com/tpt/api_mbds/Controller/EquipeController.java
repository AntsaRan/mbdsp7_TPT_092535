package com.tpt.api_mbds.Controller;

import com.tpt.api_mbds.model.Equipe;
import com.tpt.api_mbds.repository.EquipeRepository;
import com.tpt.api_mbds.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EquipeController {

    @Autowired
    EquipeRepository equipeRepository;

    @GetMapping("/equipes")
    public ResponseEntity<List<Equipe>> getAllEquipes(@RequestParam(required = false) String nom){
        try {
            List<Equipe> equipes = new ArrayList<Equipe>();

            if (nom == null)
                equipeRepository.findAll().forEach(equipes::add);
            else
                equipeRepository.findByNomContaining(nom).forEach(equipes::add);


            if (equipes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(equipes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/equipes/getAll")
    public ResponseEntity<Response<Equipe>> getAll(@RequestParam(required = false) String nom){
        try {
            List<Equipe> equipes = new ArrayList<Equipe>();
            Response<Equipe> retour=new Response<>();
            if (nom == null)
                equipeRepository.findAll().forEach(equipes::add);
            else
                equipeRepository.findByNomContaining(nom).forEach(equipes::add);


            if (equipes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            retour.setPage(1);
            retour.setResults(equipes);
            return new ResponseEntity<>(retour, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

  /*  @GetMapping("/equipes/{id}")
    public ResponseEntity<Equipe> getEquipeById(@PathVariable("id") String id) {

    }

   */


    @PostMapping(path = "/equipe")
    public ResponseEntity<Equipe> createEquipe(@RequestBody Equipe equipe) {
        try {
            System.out.println("Makato izy" + equipe.getNom());
            //header="application/json";
            Equipe _equipe = equipeRepository.save(new Equipe(equipe.getNom(), equipe.getLogo()));

            return new ResponseEntity<>(_equipe, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/equipe/{id}")
    public ResponseEntity<Equipe> updateEquipe(@PathVariable("id") String id,@RequestBody Equipe equipe) {
        System.out.println(" Makato am PUT id : "+id);
        Optional<Equipe> equipeData = equipeRepository.findById(id);

        if (equipeData.isPresent()) {
            Equipe _tutorial = equipeData.get();
            _tutorial.setNom(equipe.getNom());
            _tutorial.setLogo(equipe.getLogo());
            System.out.println(" Team updated id : "+id);
            return new ResponseEntity<>(equipeRepository.save(_tutorial), HttpStatus.OK);
        } else {
            System.out.println("no Team found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/equipe/{id}")
    public ResponseEntity<HttpStatus> deleteEquipe(@PathVariable("id")String id) {
        try {
            equipeRepository.deleteById(id);
            System.out.println(" Team deleted id : "+id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/equipes")
    public ResponseEntity<HttpStatus> deleteAllEquipes() {
        try {
            equipeRepository.deleteAll();
            System.out.println("All Team deleted");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

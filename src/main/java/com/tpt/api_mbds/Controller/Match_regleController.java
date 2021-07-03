package com.tpt.api_mbds.Controller;

import com.tpt.api_mbds.model.Match_regle;
import com.tpt.api_mbds.repository.Match_regleRepository;
import org.bson.types.ObjectId;
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
public class Match_regleController {
    @Autowired
    Match_regleRepository match_regleRepository;

    @GetMapping("/matchRegles")
    public ResponseEntity<List<Match_regle>> getAllMatch_regle(){
        try {
            List<Match_regle> match_regle = new ArrayList<Match_regle>();


                match_regleRepository.findAll().forEach(match_regle::add);



            if (match_regle.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(match_regle, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/matchRegles/{idMatch}")
    public ResponseEntity<Match_regle> getMatch_regleByIdMatch(@PathVariable("idMatch") String idMatch) {
        try {
            System.out.println("ITO ILAY STRING "+idMatch);
            ObjectId idMatchRecherche = new ObjectId(idMatch);
            Match_regle match_regleData = match_regleRepository.findMatch_regleByIdMatch(idMatchRecherche);

            System.out.println("ITO Raha nahita izy  "+match_regleData.getId());

            if (match_regleData!=null) {
                return new ResponseEntity<>(match_regleData, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            throw e;
           // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

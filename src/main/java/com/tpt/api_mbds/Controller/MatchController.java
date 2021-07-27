package com.tpt.api_mbds.Controller;


import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.google.gson.Gson;
import com.tpt.api_mbds.model.*;
import com.tpt.api_mbds.repository.*;
import com.tpt.api_mbds.response.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MatchController {
    @Autowired
    MatchRepository matchRepository;
    @Autowired
    EquipeRepository equipeRepository;
    @Autowired
    Match_regleRepository match_regleRepository;
    @Autowired
    RegleRepository regleRepository;
    @Autowired
    Match_regleToInsertRepository match_regleToInsertRepository;

    @GetMapping("/matches")
    public ResponseEntity<List<Match>> getAllMatches(@RequestParam(required = false) String etat ) throws ParseException {
        try {
            List<Match> matchs = new ArrayList<Match>();


            if (etat == null )
                matchRepository.findAll().forEach(matchs::add);

            else
                matchRepository.findMatchByEtatContaining(etat).forEach(matchs::add);


            if (matchs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(matchs, HttpStatus.OK);
        } catch (Exception e) {
            throw e;

        }
    }


    @GetMapping("/matchespardate/{date}")
    public ResponseEntity<List<Match>> getMatchesParDate(@PathVariable("date") String date) throws ParseException {
        try {
            List<Match> matchs = new ArrayList<Match>();
            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            ISO8601DateFormat df = new ISO8601DateFormat();
            Date date1 = df.parse(date);
            LocalDate localdate1 = date1.toInstant().atZone(ZoneId.of("Europe/Moscow")).toLocalDate();
            Date date11 = Date.from(localdate1.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant());
            LocalDate localdate2 = date1.toInstant().atZone(ZoneId.of("Europe/Moscow")).toLocalDate();

            //Date todayDate = new Date();
            //System.out.println("ITO ILAY Date1  "+date1);
            //System.out.println("ITO ILAY Date1 Faharoa  "+date11);
            //System.out.println("ITO ILAY DateAndroany  "+todayDate);
            //System.out.println("ITO ILAY LOCALDate2  "+localdate2);
            localdate2 = localdate2.plusDays(1);
            //System.out.println("ITO ILAY LOCALDate2 + 1  "+localdate2);
            Date date2 = Date.from(localdate2.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant());
            //System.out.println("ITO ILAY Date2  "+date2);
            //System.out.println("ZONE ID  "+ZoneId.of("Europe/Moscow"));
            matchRepository.findMatchesByDateBetween(date11,date2).forEach(matchs::add);
            if (matchs.isEmpty()) {
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(matchs, HttpStatus.OK);
        } catch (Exception e) {
           throw e;
        }
    }


    @GetMapping("/matches/equipe/{id}")
    public ResponseEntity<List<Match>> getMatchesParEquipe(@PathVariable("id") String id){
        try {
            List<Match> matchs = new ArrayList<Match>();

                System.out.println("ITO ILAY ID EQUIPE "+id);
                matchRepository.findMatchByEquipe1_Id(id).forEach(matchs::add);
                matchRepository.findMatchByEquipe2_Id(id).forEach(matchs::add);


            if (matchs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(matchs, HttpStatus.OK);
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
    public ResponseEntity<Match> createMatch(@RequestBody MatchToInsert matchToInsert) {
        try {
            System.out.println("ID EQUIPE1 voalohany "+matchToInsert.getIdEquipe1());
            Optional<Equipe> equipe1Data = equipeRepository.findById(matchToInsert.getIdEquipe1());
            Optional<Equipe> equipe2Data = equipeRepository.findById(matchToInsert.getIdEquipe2());
            //System.out.println(match.getEquipe1());
            //System.out.println(match.getEquipe2());
            //System.out.println(match.getDate());
           // System.out.println(match.getEtat());
            //System.out.println(match.getScoreEquipe1());

            //header="application/json";
            if (equipe1Data.isPresent() && equipe2Data.isPresent()){
                List<RegleCote> reglesCote=new ArrayList<>();
                List<Regle> regle=regleRepository.findAllByOrderByOrdreAsc();
                int size=regle.size();

                Random random = new Random();
                //int randomNum = ThreadLocalRandom.current().nextInt(30, 100 + 1);

                for(int i=0;i<size;i++){
                    RegleCote r1=new RegleCote(regle.get(i), (double) random.nextInt(8));
                    reglesCote.add(r1);
                }

                Match _match = matchRepository.save(new Match(equipe1Data.get(),equipe2Data.get(),matchToInsert.getDate(),matchToInsert.getLieu(),"1",0,0,0,0,0,0));
                ObjectId objId = new ObjectId(_match.getId());
                Match_regleToInsert match_regleToInsert=match_regleToInsertRepository.save(new Match_regleToInsert(objId,reglesCote));
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
            _match.setCornerEquipe1(match.getCornerEquipe1());
            _match.setCornerEquipe2(match.getCornerEquipe2());
            _match.setPossessionEquipe1(match.getPossessionEquipe1());
            _match.setPossessionEquipe2(match.getPossessionEquipe2());


            System.out.println(" Team updated id : "+id);
            return new ResponseEntity<>(matchRepository.save(_match), HttpStatus.OK);
        } else {
            System.out.println("no match found");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    /*
    @PutMapping("/matchEtat/{id}")
    public ResponseEntity<Match> updateMatchEtat(@PathVariable("id") String id,@RequestBody String etat) {
        System.out.println(" Makato am PUT id : "+id);
        Optional<Match> matchData = matchRepository.findById(id);

        if (matchData.isPresent()) {
            Match _match = matchData.get();

            _match.setEtat(etat);



            System.out.println(" Team updated id : "+id);
            return new ResponseEntity<>(matchRepository.save(_match), HttpStatus.OK);
        } else {
            System.out.println("no match found");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/matchEtatScores/{id}")
    public ResponseEntity<Match> updateMatchEtatScores(@PathVariable("id") String id,@RequestBody(required = false) String etat ,@RequestBody Integer scoreEquipe1,@RequestBody Integer scoreEquipe2,@RequestBody Integer cornerEquipe1,@RequestBody Integer cornerEquipe2,@RequestBody Integer possessionEquipe1,@RequestBody Integer possessionEquipe2 ) {
        System.out.println(" Makato am PUT id : "+id);
        Optional<Match> matchData = matchRepository.findById(id);

        if (matchData.isPresent()) {
            Match _match = matchData.get();

            if(etat!=null){  _match.setEtat(etat);}

            _match.setScoreEquipe1(scoreEquipe1);
            _match.setScoreEquipe2(scoreEquipe2);
            _match.setCornerEquipe1(cornerEquipe1);
            _match.setCornerEquipe2(cornerEquipe2);
            _match.setPossessionEquipe1(possessionEquipe1);
            _match.setPossessionEquipe2(possessionEquipe2);


            System.out.println(" Team updated id : "+id);
            return new ResponseEntity<>(matchRepository.save(_match), HttpStatus.OK);
        } else {
            System.out.println("no match found");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
*/
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


    //Partie Mobile
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

    @GetMapping("/matches/{id}")
    public ResponseEntity<Response<Match>> getMatchByIdResult(@PathVariable("id") String id) {
        Optional<Match> matchData = matchRepository.findById(id);
        Response<Match> retour=new Response<>();
        List<Match> matchList=new ArrayList<>();
        if (matchData.isPresent()) {
            matchList.add(matchData.get());
            retour.setResults(matchList);
            retour.setPage(1);
            return new ResponseEntity<>(retour, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/matchespardateM/{date}")
    public ResponseEntity<Response<Match>> getMatchesParDateM(@PathVariable("date") String date) throws ParseException {
        try {
            List<Match> matchs = new ArrayList<Match>();
            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            ISO8601DateFormat df = new ISO8601DateFormat();
            Date date1 = df.parse(date);
            LocalDate localdate1 = date1.toInstant().atZone(ZoneId.of("Europe/Moscow")).toLocalDate();
            Date date11 = Date.from(localdate1.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant());
            LocalDate localdate2 = date1.toInstant().atZone(ZoneId.of("Europe/Moscow")).toLocalDate();
            localdate2 = localdate2.plusDays(1);
            Date date2 = Date.from(localdate2.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant());

            Response<Match> retour=new Response<>();

            matchRepository.findMatchesByDateBetween(date11,date2).forEach(matchs::add);
            if (matchs.isEmpty()) {
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
            }
            retour.setResults(matchs);
            retour.setPage(1);
            return new ResponseEntity<>(retour, HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }


    ///////////////////////////CROWN/////////////////////////////
    @PutMapping(path="/StartMatch/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> startCron(@PathVariable("id") String id) throws SQLException {
        try {
            List<Match> matchs = new ArrayList<Match>();
            String etat="1";
            Date date1 = new Date();
            LocalDate localdate1 = date1.toInstant().atZone(ZoneId.of("Europe/Moscow")).toLocalDate();
            Date date11 = Date.from(localdate1.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant());
            LocalDate localdate2 = date1.toInstant().atZone(ZoneId.of("Europe/Moscow")).toLocalDate();


            System.out.println("ITO ILAY Date1  "+date1);
            System.out.println("ITO ILAY Date1 Faharoa  "+date11);

            System.out.println("ITO ILAY LOCALDate2  "+localdate2);
            localdate2 = localdate2.plusDays(1);
            System.out.println("ITO ILAY LOCALDate2 + 1  "+localdate2);
            Date date2 = Date.from(localdate2.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant());
            System.out.println("ITO ILAY Date2  "+date2);
            System.out.println("ZONE ID  "+ZoneId.of("Europe/Moscow"));
            matchRepository.findMatchesByDateBetweenAndEtatIs(date11,date2,etat).forEach(matchs::add);

            if (matchs.isEmpty()) {
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
            }
            int size=matchs.size();
            System.out.println("Taille liste = "+size);
            Timer t = new Timer();
            for(int i=0;i<size;i++){
                TimerExample start = new TimerExample("start", matchs.get(0),matchRepository);
                t.scheduleAtFixedRate(start, 0, 10 * 1000);
            }
            for(int i=0;i<size;i++){
                TimerExample end = new TimerExample("end",matchs.get(i),matchRepository);
                t.scheduleAtFixedRate(end, 10000, 1000);
            }
            return new ResponseEntity<>(new String("MATCHES STARTED"), HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }

        /*
         ///////////////////////////CROWN/////////////////////////////
    @PutMapping(path="/StartMatch/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> startCron(@PathVariable("id") String id) throws SQLException {
        try {
            Optional<Match> matchData = matchRepository.findById(id);
            if (matchData.isPresent()) {
                TimerExample start = new TimerExample("start", matchData.get(),matchRepository);
                TimerExample end = new TimerExample("end", matchData.get(),matchRepository);
                Timer t = new Timer();
                t.scheduleAtFixedRate(start, 0, 10 * 1000);

                t.scheduleAtFixedRate(end, 10000, 1000);
                return new ResponseEntity<>(new Gson().toJson(matchData.get()), HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            throw e;
        }
        */
    }
}

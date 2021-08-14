package com.tpt.api_mbds.Controller;


import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.google.gson.Gson;
import com.sun.jdi.event.ExceptionEvent;
import com.tpt.api_mbds.ApiMbdsApplication;
import com.tpt.api_mbds.model.*;
import com.tpt.api_mbds.repository.*;
import com.tpt.api_mbds.response.Response;
import oracle.jdbc.OracleConnection;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    PariController pariController = new PariController();
    UserController userController=new UserController();

    @Autowired
    private RestTemplate restTemplate1;

    @Bean
    public RestTemplate restTemplate1() {
        return new RestTemplate();
    }
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

            localdate2 = localdate2.plusDays(1);

            Date date2 = Date.from(localdate2.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant());

            matchRepository.findMatchesByDateBetween(date11,date2).forEach(matchs::add);
            if (matchs.isEmpty()) {
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(matchs, HttpStatus.OK);
        } catch (Exception e) {
           throw e;
        }
    }

    @GetMapping("/matchesOrder")
    public ResponseEntity<List<Match>> getAllMatchesOrder(@RequestParam(required = true) String etat ) throws ParseException {
        try {
            List<Match> matchs = new ArrayList<Match>();


            if (etat == null )
                matchRepository.findAllByDateOrderByDateAsc().forEach(matchs::add);

            else
                matchRepository.findMatchesByEtatContainingOrderByDateAsc(etat).forEach(matchs::add);


            if (matchs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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

    @GetMapping("/matchesPlusParie")
    public ResponseEntity<List<Match>> getMatchesPlusParie() throws SQLException {
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();

            List<Match> matchs = new ArrayList<Match>();
            PariController pariController = new PariController();

            List<String> listeIdMatch=pariController.getTopPariByMatch(oracleConnection);
            Integer taille=listeIdMatch.size();
            System.out.println("Taille "+taille);

            for(int i=0;i<taille;i++){
                Match val=matchRepository.findByIdAndEtat(listeIdMatch.get(i),"1");
                if(val!=null){matchs.add(val);}
            }



            if (matchs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(matchs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        finally {
         if(oracleConnection!=null)   oracleConnection.close();
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
                    RegleCote r1=new RegleCote(regle.get(i), (double) random.nextInt(8+ 1 - 2) + 2);
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


            //System.out.println("ITO ILAY Date1  "+date1);
            //System.out.println("ITO ILAY Date1 Faharoa  "+date11);

            //System.out.println("ITO ILAY LOCALDate2  "+localdate2);
            localdate2 = localdate2.plusDays(1);
            //System.out.println("ITO ILAY LOCALDate2 + 1  "+localdate2);
            Date date2 = Date.from(localdate2.atStartOfDay(ZoneId.of("Europe/Moscow")).toInstant());
            //System.out.println("ITO ILAY Date2  "+date2);
            //System.out.println("ZONE ID  "+ZoneId.of("Europe/Moscow"));
            matchRepository.findMatchesByDateBetweenAndEtatIs(date11,date2,etat).forEach(matchs::add);

            if (matchs.isEmpty()) {
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
            }
            int size=matchs.size();
            //System.out.println("Taille liste = "+size);
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
    }
/////////////////////Cron with Onl One Match //////////////////////
    @PutMapping(path="/StartOneMatch/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> startCronPerMatch(@PathVariable("id") String id) throws Exception {
        try {
            Optional<Match> matchData = matchRepository.findById(id);


            if (matchData.isEmpty()) {
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
            }

            Match match=new Match();
            match.endmatch(matchData.get(),matchRepository);

                //Timer t = new Timer();

                //TimerExample start = new TimerExample("start", matchData.get(),matchRepository);
                //t.scheduleAtFixedRate(start, 0, 10 * 1000);


               // TimerExample end = new TimerExample("end",matchData.get(),matchRepository);
                //t.scheduleAtFixedRate(end, 10000, 1000);

                ///////////////Ito no manao anlay Perte sy Gain/////////////
            String response=this.distribuerGain(id);
            ReturnMessage returnMessage =new ReturnMessage("done");

            return new ResponseEntity<>(new Gson().toJson(returnMessage), HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }



    ////////////////////////Distribuer Gain If True//////////////////////
    public String distribuerGain(String idMatch) throws Exception {
        List<Pari> listePari= this.getAllParibyMatch(idMatch);
        if(listePari.isEmpty()){return "Pas de paris effectués pour ce match";}
        int size=listePari.size();
        for(int i=0;i<size;i++){

            Pari pari1=listePari.get(i);

            //System.out.println("ILAY PARI faha "+i+" ID "+pari1.getId()+" ny ID REGLE_ANY "+pari1.getMatchRegle());
            String ordre = this.getOrdreByIdRegle(pari1.getMatchRegle());
            //System.out.println("ILAY ORDRE "+ordre);

            boolean isWinner=this.testRegleGagnant(idMatch,ordre);
            if(isWinner){
                Integer coteAnlayMatch=this.getOrdreByIdRegleEnChiffre(pari1.getMatchRegle());
                //System.out.println("Ordre de la cote "+coteAnlayMatch);
                Double coteSurlaquelleOnAParier=this.getCote(idMatch,coteAnlayMatch);
                //System.out.println("COTE NANAOVANA PARI == "+coteSurlaquelleOnAParier);
                Integer accroissement =pari1.getMise()*coteSurlaquelleOnAParier.intValue();
                //System.out.println("accroissement == "+accroissement);
                //System.out.println("mise anlay User == "+pari1.getMise());
                userController.AjoutJeton(accroissement,pari1.getIdUtilisateur());
                Historique_Jetons historique_jetons=new Historique_Jetons(pari1.getIdUtilisateur(),3,accroissement,pari1.getId());
                Historique_jetonsController historique_jetonsController=new Historique_jetonsController();
                String valinyHisto=historique_jetonsController.insertHistorique(historique_jetons);
                sendNotificationWebToAllDeviceForUser(String.valueOf(pari1.getIdUtilisateur()),"FootBet","Felicitations vous avez gagné votre pari");
            }


        }

        //System.out.println("ITO ILAY COTE NANAOVANY PARI");
        return "Match demarré";

    }



    //////////////////////API TEST////////////////////////////
    @GetMapping("/TestApi/{id}")
    public ResponseEntity<Response<Match>> TestNEWFUNCTION(@PathVariable("id") String idMatch) throws Exception {
       List<Pari> listePari= this.getAllParibyMatch(idMatch);
        if(listePari.isEmpty()){return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);}
       int size=listePari.size();
       for(int i=0;i<size;i++){

           Pari pari1=listePari.get(i);

           //System.out.println("ILAY PARI faha "+i+" ID "+pari1.getId()+" ny ID REGLE_ANY "+pari1.getMatchRegle());
           String ordre = this.getOrdreByIdRegle(pari1.getMatchRegle());
           //System.out.println("ILAY ORDRE "+ordre);

           boolean isWinner=this.testRegleGagnant(idMatch,ordre);
           if(isWinner){
               Integer coteAnlayMatch=this.getOrdreByIdRegleEnChiffre(pari1.getMatchRegle());
               //System.out.println("Ordre de la cote "+coteAnlayMatch);
               Double coteSurlaquelleOnAParier=this.getCote(idMatch,coteAnlayMatch);
               //System.out.println("COTE NANAOVANA PARI == "+coteSurlaquelleOnAParier);
               Integer accroissement =pari1.getMise()*coteSurlaquelleOnAParier.intValue();
               //System.out.println("accroissement == "+accroissement);
               //System.out.println("mise anlay User == "+pari1.getMise());
               userController.AjoutJeton(accroissement,pari1.getIdUtilisateur());
            }

       }

       //System.out.println("ITO ILAY COTE NANAOVANY PARI");
        return new ResponseEntity<>(null,HttpStatus.OK);

    }


    /////////////////////////////FONCTION EXTERNE///////////////////////////////
    public ArrayList<Pari> getAllParibyMatch(String id) throws SQLException {
        ArrayList<Pari> list=new ArrayList<Pari>();
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
            list = pariController.getAllParisbyIdMatch(oracleConnection,id);

            //System.out.println("La liste n'est pas vide ex: " + list.get(0).getId());
            return list;

        } catch (Exception throwables) {
            throw throwables;
        }
        finally {
            oracleConnection.close();
        }
    }

    public String getOrdreByIdRegle(String idRegle){
        try {
            Optional<Regle> regleData = regleRepository.findById(idRegle);
            if (regleData.isEmpty()) {
                return null;
            }
            Integer val= regleData.get().getOrdre();
            String valiny="";
            if(val==1)valiny="S1";
            if(val==2)valiny="S0";
            if(val==3)valiny="S2";
            if(val==4)valiny="C1";
            if(val==5)valiny="C0";
            if(val==6)valiny="C2";
            if(val==7)valiny="P1";
            if(val==8)valiny="P0";
            if(val==9)valiny="P2";
            return valiny;
        }
        catch (Exception e){
           throw e;
        }
    }

    public Integer getOrdreByIdRegleEnChiffre(String idRegle){
        try {
            Optional<Regle> regleData = regleRepository.findById(idRegle);
            if (regleData.isEmpty()) {
                return null;
            }
            Integer val= regleData.get().getOrdre();

            return val;
        }
        catch (Exception e){
            throw e;
        }
    }



        public boolean testRegleGagnant(String idMatch,String ordre) throws Exception {
        boolean reponse=false;
        try {
            List<String> valiny = new ArrayList<>();
            Optional<Match> matchData = matchRepository.findById(idMatch);
            if(matchData.isEmpty()){throw new Exception("Pas de Match Correspondant au Pari");}
            Match match = matchData.get();
            if (match.getScoreEquipe1() > match.getScoreEquipe2()) valiny.add("S1");
            if (match.getScoreEquipe1() < match.getScoreEquipe2()) valiny.add("S2");
            if (match.getScoreEquipe1().equals(match.getScoreEquipe2())) valiny.add("S0");
            System.out.println("Nombre Corner Eq1 " + match.getCornerEquipe1());
            System.out.println("Nombre Corner Eq2 "  + match.getCornerEquipe2());
            if (match.getCornerEquipe1() > match.getCornerEquipe2()) valiny.add("C1");
            if (match.getCornerEquipe1() < match.getCornerEquipe2()) valiny.add("C2");
            if (match.getCornerEquipe1().equals(match.getCornerEquipe2())) valiny.add("C0");
            if (match.getPossessionEquipe1() > match.getPossessionEquipe2()) valiny.add("P1");
            if (match.getPossessionEquipe1() < match.getPossessionEquipe2()) valiny.add("P2");
            if (match.getPossessionEquipe1().equals(match.getPossessionEquipe2())) valiny.add("P0");

            System.out.println("L'interieur 1 du String " + valiny.get(0));
            System.out.println("L'interieur 2 du String " + valiny.get(1));
            System.out.println("L'interieur 3 du String " + valiny.get(2));

            reponse = this.testGagnant(valiny, ordre);
            System.out.println("ISWINNER " + reponse);
        }
        catch (Exception e){
            throw e;
        }
        return reponse;
        }

        public boolean testGagnant(List<String> liste,String ordre){
            boolean valiny=false;
            int size=liste.size();
            for(int i=0;i<size;i++){
                if(liste.get(i)==ordre){valiny=true;}
            }
            return valiny;
        }

        public Double getCote(String idMatch,int ordrePourPari) throws Exception {
        Double valiny=0.0;
            try {
                ObjectId idMatchRecherche = new ObjectId(idMatch);
                Optional<Match_regle> match_regleData = Optional.ofNullable(match_regleRepository.findMatch_regleByIdMatch(idMatchRecherche));
                if(match_regleData.isEmpty()){
                    throw new Exception("Match Introuvable");
                }
               // System.out.println("ITO ILAY MATCH_REGLE ID "+match_regleData.get().getId());

                valiny=match_regleData.get().getRegles().get(ordrePourPari-1).getCote();
                //valiny=match_regleData.get().getRegles().indexOf(ordrePourPari);


                return valiny;
            }
            catch (Exception e){
                throw e;
            }
        }


        ///send notification
        int sendNotificationToWeb(String token,String idUser,String title,String message) throws JSONException {
            String url = "https://fcm.googleapis.com/fcm/send";
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("Authorization", "key=AAAA6b_i18Q:APA91bFj2WxBG2Aro1rv71cCm7rKp0vX7vTmLeBObUNQFUl8eYNAWBYRy8ViRUhbqi-iYDtx-I4ILb9B7x71wBfJucHWEGeX0TSctx_1r4u50xNt5lpz_akgVKVDwJpGmAPRHx1EBEI7");
            headers.add("Content-Type","application/json");
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");


            MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

            JSONObject data = new JSONObject()
                    .put("idUser",idUser)
                    .put("title", title)
                    .put("body", message);

            String jsonBody = new JSONObject()
                    .put("to", token)
                    .put("data", data)
                    .toString();


            System.out.println("jsonBody "+jsonBody);





            HttpEntity<?> httpEntity = new HttpEntity<Object>(jsonBody, headers);

            ResponseEntity response = restTemplate1.exchange(url, HttpMethod.POST, httpEntity, String.class);
            JSONObject json = new JSONObject(response.getBody().toString());
            int val = json.getInt("success");
            return val;
        }
//////////////

    void sendNotificationWebToAllDeviceForUser(String idUser,String title,String message) throws SQLException, JSONException{
        ArrayList<Device> listeToken = Device.getAllNotifWeb(idUser);
        System.out.println("listeToken :"+listeToken.size());
        for(int i =0;i<listeToken.size();i++){
            int val = sendNotificationToWeb(listeToken.get(i).getToken(),idUser,title,message);
            if(val==1)
                System.out.println("Notification envoyé");
        }
    }

}

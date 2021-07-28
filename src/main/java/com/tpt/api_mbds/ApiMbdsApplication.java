package com.tpt.api_mbds;

import com.google.gson.Gson;
import com.tpt.api_mbds.Controller.PariController;
import com.tpt.api_mbds.Controller.SuperAdminController;
import com.tpt.api_mbds.Controller.UserController;
import com.tpt.api_mbds.model.*;
import com.tpt.api_mbds.repository.MatchRepository;
import com.tpt.api_mbds.response.Response;
import oracle.jdbc.OracleConnection;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.*;

@SpringBootApplication
@CrossOrigin
@RequestMapping("/oracle")
public class ApiMbdsApplication {
    UserController userController = new UserController();
    PariController pariController = new PariController();
    SuperAdminController superAdminController= new SuperAdminController();
    @Autowired
    MatchRepository matchRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }




    @GetMapping(path="/getAllUser",produces = "application/json")
    @ResponseBody
    public String getAllUser() throws SQLException {
        Utilisateur val = new Utilisateur();
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();

            val = userController.getAllUser(oracleConnection);
            System.out.println("ito" + val);

            return new Gson().toJson(val);

        } catch (Exception throwables) {
            throw throwables;
        }
        finally {
            oracleConnection.close();
        }
    }

    @PostMapping(path="/authentification",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> authentification(String mail, String pwd) throws SQLException {
        Utilisateur val = new Utilisateur();
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
           // System.out.println("le mail de l'user " + mail);
            val = userController.authentification(oracleConnection,mail,pwd);
            if(val.getId()==0){
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
            }
            //System.out.println("RESULT APRES LE GET " + val.getPrenom());
            return new ResponseEntity<>(new Gson().toJson(val), HttpStatus.OK);


        } catch (Exception throwables) {
            throw throwables;
        }
        finally {
            oracleConnection.close();
        }
    }

    @PostMapping(path="/authentificationAdmin",consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> authentificationAdmin(@RequestBody SuperAdmin admin) throws SQLException {

        SuperAdmin val = new SuperAdmin();
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
            // System.out.println("le mail de l'user " + mail);
            val = superAdminController.authentification(oracleConnection,admin.getPseudo(),admin.getPassword());
            if(val.getId()==0){
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
            }
            //System.out.println("RESULT APRES LE GET " + val.getPrenom());
            return new ResponseEntity<>(new Gson().toJson(val), HttpStatus.OK);


        } catch (Exception throwables) {
            throw throwables;
        }
        finally {
            oracleConnection.close();
        }
    }



    @PostMapping(path="/insertPari",consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> insertPari(@RequestBody Pari pari) throws SQLException, ParseException {

        OracleConnection oracleConnection = null;
        try {

            oracleConnection = Connexion.getConnection();
             //System.out.println("IDMATCH ANLAY PARI " + pari.getIdMatch());
             //System.out.println("DATE ANLAY PARI " + pari.getDateParis());
            String response=pariController.insertPari(oracleConnection,pari);

            //System.out.println("RESULT APRES LE GET " + val.getPrenom());
            return new ResponseEntity<>(response, HttpStatus.OK);


        } catch (Exception throwables) {
            throw throwables;
        }
        finally {
            oracleConnection.close();
        }
    }
    @GetMapping(path="/getAllParis",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> getAllParis() throws SQLException {
        ArrayList<Pari> list=new ArrayList<Pari>();
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();

            list = pariController.getAllParis(oracleConnection);
            if(list.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            System.out.println("La liste n'est pas vide ex: " + list.get(0).getId());

            return new ResponseEntity<>(new Gson().toJson(list), HttpStatus.OK);

        } catch (Exception throwables) {
            throw throwables;
        }
        finally {
            oracleConnection.close();
        }
    }

    @GetMapping(path="/getAllParisbyUser/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> getAllParisbyUser(@PathVariable("id") int id) throws SQLException {
        ArrayList<Pari> list=new ArrayList<Pari>();
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
            list = pariController.getAllParisbyUser(oracleConnection,id);
            if(list.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            System.out.println("La liste n'est pas vide ex: " + list.get(0).getId());
            return new ResponseEntity<>(new Gson().toJson(list), HttpStatus.OK);

        } catch (Exception throwables) {
            throw throwables;
        }
        finally {
            oracleConnection.close();
        }
    }

    @GetMapping(path="/getAllParisbyIdMatch/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> getAllParisbyIdMatch(@PathVariable("id") String id) throws SQLException {
        ArrayList<Pari> list=new ArrayList<Pari>();
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
            list = pariController.getAllParisbyIdMatch(oracleConnection,id);
            if(list.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            System.out.println("La liste n'est pas vide ex: " + list.get(0).getId());
            return new ResponseEntity<>(new Gson().toJson(list), HttpStatus.OK);

        } catch (Exception throwables) {
            throw throwables;
        }
        finally {
            oracleConnection.close();
        }
    }

    @PostMapping(path="/insertUser",consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> insertUser(@RequestBody Utilisateur utilisateur) throws SQLException, ParseException {

        OracleConnection oracleConnection = null;
        try {

            oracleConnection = Connexion.getConnection();
            //System.out.println("Nom ANLAY USER " + utilisateur.getNom());
            //System.out.println("DATENAISS ANLAY USER JSON " + utilisateur.getDateNaissance());

            String reponse=userController.insertUser(oracleConnection,utilisateur);

            //System.out.println("RESULT APRES LE GET " + val.getPrenom());
            return new ResponseEntity<>(reponse, HttpStatus.OK);


        } catch (Exception throwables) {
            throw throwables;
        }
        finally {
            oracleConnection.close();
        }
    }

    //GetMise
    @GetMapping(path="/getAllMise/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> getAllMiseByUser(@PathVariable("id") int id) throws SQLException {
        float result=0f;
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
            result = pariController.getAllMisebyUser(oracleConnection,id);
            if(result==0f){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            System.out.println("La somme mise: " + result);
            return new ResponseEntity<>(new Gson().toJson(result), HttpStatus.OK);

        } catch (Exception throwables) {
            throw throwables;
        }
        finally {
            oracleConnection.close();
        }
    }
    /////////////////API MOBILE/////////////////////
    @GetMapping(path="/getAllParisbyUserM/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> getAllParisbyUserM(@PathVariable("id") int id) throws SQLException {
        Response<Pari> result = new Response<>();
        ArrayList<Pari> list=new ArrayList<Pari>();
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
            list = pariController.getAllParisbyUser(oracleConnection,id);
            result.setResults(list);
            result.setPage(1);
            if(list.isEmpty()){

                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            System.out.println("La liste n'est pas vide ex: " + list.get(0).getId());
            return new ResponseEntity<>(new Gson().toJson(result), HttpStatus.OK);

        } catch (Exception throwables) {
            throw throwables;
        }
        finally {
            oracleConnection.close();
        }
    }
    @GetMapping(path="/getAllMiseM/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> getAllMiseByUserM(@PathVariable("id") int id) throws SQLException {
        float result=0f;
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
            result = pariController.getAllMisebyUser(oracleConnection,id);
            if(result==0f){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            System.out.println("La somme mise: " + result);
            return new ResponseEntity<>(new Gson().toJson(result), HttpStatus.OK);

        } catch (Exception throwables) {
            throw throwables;
        }
        finally {
            oracleConnection.close();
        }
    }

    ///////////////////////////CROWN/////////////////////////////
   /* @GetMapping(path="/StartMatch/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> startCron(@PathVariable("id") String id) throws SQLException {
        try {
            Optional<Match> matchData = matchRepository.findById(id);
            if(matchData.isPresent()){
                TimerExample start = new TimerExample("start", matchData.get());
                TimerExample end = new TimerExample("end", matchData.get());
                Timer t = new Timer();
                t.scheduleAtFixedRate(start, 0, 10 * 1000);
                t.scheduleAtFixedRate(end, 10000, 1000);
                return new ResponseEntity<>(new Gson().toJson(matchData.get()), HttpStatus.OK);
            }
            else   return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        }
        catch (Exception e){
            throw e;
        }
    } */
// SEND NOTIFICATION
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

        ResponseEntity response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        JSONObject json = new JSONObject(response.getBody().toString());
        int val = json.getInt("success");
        return val;
    }
//////////////TEST/////////////////////////////////////

    @PostMapping(value = "/testNotif", consumes = "application/json", produces = "application/json")
    @ResponseBody
    void sendNotificationWebToAllDeviceForUserPro(@RequestParam(required = true) String idUser,@RequestParam(required = true) String title,@RequestParam(required = true) String message) throws SQLException, JSONException{
        ArrayList<Device> listeToken = Device.getAllNotifWeb(idUser);
        System.out.println("listeToken :"+listeToken.size());
        for(int i =0;i<listeToken.size();i++){
            int val = sendNotificationToWeb(listeToken.get(i).getToken(),idUser,title,message);
            if(val==1)
                System.out.println("Notification envoyé");
        }
    }
    @PostMapping(value = "/insererDevice", consumes = "application/json", produces = "application/json")
    @ResponseBody
    String insererNotifWeb(@RequestBody Device n) throws SQLException{
        OracleConnection co = Connexion.getConnection();
        String val = "";
        try{
            int i = Device.getDoublonNotifWeb(co,n.getIdUtilisateur(),n.getToken());
            if(i==0){
                Device.insererNotifWeb(co,n.getIdUtilisateur(),n.getToken());
                val = "New device detected";
            }
            else
                val = "device already in database";
        }
        finally{
            co.close();
        }
        return val;
    }
    public static ArrayList<Device> getAllNotifWeb(String idUser) throws SQLException{
        OracleConnection co = Connexion.getConnection();
        ArrayList<Device> val = new ArrayList();
        Statement statement = null;

        try{
            statement = co.createStatement();

            ResultSet resultSet = statement.executeQuery("select TOKEN from DEVICE where IDUTILISATEUR ='"+idUser+"' ");
            while (resultSet.next()){
                Device temp = new Device(idUser,resultSet.getString(1));
                //int idMatch, int idTeam1, int idTeam2, Date datematch, int nbrMap, String nomTeam1, String nomTeam2
                val.add(temp);
            }
        }
        finally{
            if(statement!=null){
                statement.close();
            }
            if(co!=null){
                co.close();
            }
        }

        return val;
    }
    public static void main(String[] args) {
       SpringApplication.run(ApiMbdsApplication.class, args);



}



}

package com.tpt.api_mbds;

import com.google.gson.Gson;
import com.tpt.api_mbds.Controller.Historique_jetonsController;
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
    public ResponseEntity<String> getAllUser() throws SQLException {
        ArrayList<Utilisateur> list = new ArrayList<Utilisateur>();
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();

            list = userController.getAllUser(oracleConnection);
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


    @GetMapping(path="/getUserbyId/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> getUserbyId(@PathVariable("id") int id)  throws SQLException {
        Utilisateur user = new Utilisateur();
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
            user = userController.getUserById(id);
            if(user.getId()==0){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            System.out.println(" pas vide ex: " + user.getId());
            return new ResponseEntity<>(new Gson().toJson(user), HttpStatus.OK);

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

    @PostMapping(path="/checkMailUser",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> chechUserMail(String mail) throws SQLException {
        Utilisateur val = new Utilisateur();
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
            // System.out.println("le mail de l'user " + mail);
            val = userController.chechUserMail(oracleConnection,mail);
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

    @PutMapping(path="/updateUser/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> updateUser(@PathVariable("id") String id ,@RequestBody Utilisateur utilisateur) throws Exception {
        Utilisateur val=new Utilisateur();
        try {
            val = userController.updateUser(Integer.valueOf(id),utilisateur);

            if(val==null){

                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            System.out.println("La liste n'est pas vide ex: " + val.getId());
            return new ResponseEntity<>(new Gson().toJson(val), HttpStatus.OK);


        }catch (Exception e){
            throw e;
        }
    }
//////////////////Achat Jetons ///////////////////////
    @PutMapping(path="/addJetonsUser/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> addJetonsUser(@PathVariable("id") String id ,@RequestParam(required = true) Integer jetons) throws Exception {
        String val="";
        try {
            Historique_Jetons historique_jetons=new Historique_Jetons(Integer.valueOf(id),1,jetons,0);
            Historique_jetonsController historique_jetonsController=new Historique_jetonsController();
            val = userController.AjoutJeton(jetons,Integer.valueOf(id));
            String valinyHisto=historique_jetonsController.insertHistorique(historique_jetons);

            if(val==""){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            System.out.println("La liste n'est pas vide ex: " + val);
            return new ResponseEntity<>(val, HttpStatus.OK);


        }catch (Exception e){
            throw e;
        }
    }

    //////////////////Vente Jetons ///////////////////////
    @PutMapping(path="/removeJetonsUser/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> removeJetonsUser(@PathVariable("id") String id ,@RequestParam(required = true) Integer jetons) throws Exception {
        String val="";
        try {
            Historique_Jetons historique_jetons=new Historique_Jetons(Integer.valueOf(id),2,jetons,0);
            Historique_jetonsController historique_jetonsController=new Historique_jetonsController();
            val = userController.EnleveJeton(jetons,Integer.valueOf(id));
            String valinyHisto=historique_jetonsController.insertHistorique(historique_jetons);

            if(val==""){

                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            System.out.println("La liste n'est pas vide ex: " + val);
            return new ResponseEntity<>(val, HttpStatus.OK);


        }catch (Exception e){
            throw e;
        }
    }

    /////////////////////update-MDP////////////////////////
    @PutMapping(path="/updateMdp/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> updateMdp(@PathVariable("id") String id ,@RequestBody String mdp) throws Exception {
        try {
           Utilisateur user= userController.getUserById(Integer.valueOf(id));
            if(user==null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            userController.updateUserMdp(Integer.valueOf(id),mdp);
            System.out.println("User mot de passe updated");
            return new ResponseEntity<>("User "+id+" password modified", HttpStatus.OK);
        }catch (Exception e){
            throw e;
        }
    }


////////////////////////////////Get Historique Jetons Par User //////////////////////
@GetMapping(path="/getHistoByUser/{id}",produces = "application/json")
@ResponseBody
public ResponseEntity<String> getHistoByUser(@PathVariable("id") int id) throws SQLException {
    ArrayList<Histo_jetons_View> result=new ArrayList<Histo_jetons_View>();
    Historique_jetonsController historique_jetonsController=new Historique_jetonsController();
    try {

        result = historique_jetonsController.getAllHistorique_JetonsbyUser(id);
        if(result.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        System.out.println("La liste Histo n'est pas vide" + result.get(0).getDateTransaction());
        return new ResponseEntity<>(new Gson().toJson(result), HttpStatus.OK);

    } catch (Exception throwables) {
        throw throwables;
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

    @GetMapping(path="/getHistoByUserM/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> getHistoByUserM(@PathVariable("id") int id) throws SQLException {
        Response<Histo_jetons_View> resultat = new Response<>();
        ArrayList<Histo_jetons_View> result=new ArrayList<Histo_jetons_View>();
        Historique_jetonsController historique_jetonsController=new Historique_jetonsController();
        try {

            result = historique_jetonsController.getAllHistorique_JetonsbyUser(id);
            if(result.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            resultat.setResults(result);
            resultat.setPage(1);
            System.out.println("La liste Histo n'est pas vide" + result.get(0).getDateTransaction());
            return new ResponseEntity<>(new Gson().toJson(resultat), HttpStatus.OK);

        } catch (Exception throwables) {
            throw throwables;
        }

    }

    @GetMapping(path="/getUserbyIdM/{id}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> getUserbyIdM(@PathVariable("id") int id)  throws SQLException {
        Response<Utilisateur> result = new Response<>();
        List <Utilisateur> list=new ArrayList<>();
        Utilisateur user = new Utilisateur();
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
            user = userController.getUserById(id);
            list.add(user);
            result.setResults(list);
            result.setPage(1);
            if(user.getId()==0){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            System.out.println(" pas vide ex: " + user.getId());
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

    @PostMapping(value = "/testNotif")
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

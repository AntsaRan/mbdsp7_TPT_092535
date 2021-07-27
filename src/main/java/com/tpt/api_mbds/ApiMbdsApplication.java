package com.tpt.api_mbds;

import com.google.gson.Gson;
import com.tpt.api_mbds.Controller.PariController;
import com.tpt.api_mbds.Controller.SuperAdminController;
import com.tpt.api_mbds.Controller.UserController;
import com.tpt.api_mbds.model.*;
import com.tpt.api_mbds.repository.MatchRepository;
import com.tpt.api_mbds.response.Response;
import oracle.jdbc.OracleConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Timer;


@SpringBootApplication
@CrossOrigin
@RequestMapping("/oracle")
public class ApiMbdsApplication {
    UserController userController = new UserController();
    PariController pariController = new PariController();
    SuperAdminController superAdminController= new SuperAdminController();
    @Autowired
    MatchRepository matchRepository;

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
            pariController.insertPari(oracleConnection,pari);

            //System.out.println("RESULT APRES LE GET " + val.getPrenom());
            return new ResponseEntity<>(null, HttpStatus.OK);


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



    public static void main(String[] args) {
       SpringApplication.run(ApiMbdsApplication.class, args);



}



}

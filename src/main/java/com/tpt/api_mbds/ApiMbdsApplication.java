package com.tpt.api_mbds;

import com.google.gson.Gson;
import com.tpt.api_mbds.Controller.PariController;
import com.tpt.api_mbds.Controller.UserController;
import com.tpt.api_mbds.model.Connexion;
import com.tpt.api_mbds.model.Fichier;
import com.tpt.api_mbds.model.Pari;
import com.tpt.api_mbds.model.Utilisateur;
import oracle.jdbc.OracleConnection;
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


@SpringBootApplication
@CrossOrigin
@RequestMapping("/oracle")
public class ApiMbdsApplication {
    UserController userController = new UserController();
    PariController pariController = new PariController();

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

    @PostMapping(path="/insertPari",consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> insertPari(@RequestBody Pari pari) throws SQLException, ParseException {

        OracleConnection oracleConnection = null;
        try {

            oracleConnection = Connexion.getConnection();
             System.out.println("IDMATCH ANLAY PARI " + pari.getIdMatch());
             System.out.println("DATE ANLAY PARI " + pari.getDateParis());
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



    public static void main(String[] args) {
       SpringApplication.run(ApiMbdsApplication.class, args);
    }



}

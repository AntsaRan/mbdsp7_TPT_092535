package com.tpt.api_mbds;

import com.google.gson.Gson;
import com.tpt.api_mbds.Controller.UserController;
import com.tpt.api_mbds.model.Connexion;
import com.tpt.api_mbds.model.Fichier;
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





@SpringBootApplication
@CrossOrigin
@RequestMapping("/oracle")
public class ApiMbdsApplication {
    UserController userController = new UserController();

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
    }

    @PostMapping(path="/authentification")
    @ResponseBody
    public ResponseEntity<String> authentification(String mail, String pwd) throws SQLException {
        Utilisateur val = new Utilisateur();
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
           // System.out.println("le mail de l'user " + mail);
            val = userController.authentification(oracleConnection,mail,pwd);
            if(val.getId()==0){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            //System.out.println("RESULT APRES LE GET " + val.getPrenom());
            return new ResponseEntity<>(new Gson().toJson(val), HttpStatus.OK);


        } catch (Exception throwables) {
            throw throwables;

        }
    }


    public static void main(String[] args) {
       SpringApplication.run(ApiMbdsApplication.class, args);
    }



}

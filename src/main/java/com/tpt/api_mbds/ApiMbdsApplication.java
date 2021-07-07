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

    @PostMapping(path="/authentification",produces = "application/json")
    @ResponseBody
    public String authentification(@RequestBody String mail,@RequestBody String password) throws SQLException {
        Utilisateur val = new Utilisateur();
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
            System.out.println("le mail de l'user " + mail);
            val = userController.authentification(oracleConnection,mail,password);
            return new Gson().toJson(val);

        } catch (Exception throwables) {
            throw throwables;

        }
    }


    public static void main(String[] args) {
       SpringApplication.run(ApiMbdsApplication.class, args);
    }



}

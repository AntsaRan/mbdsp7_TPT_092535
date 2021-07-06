package com.tpt.api_mbds;

import com.google.gson.Gson;
import com.tpt.api_mbds.model.Connexion;
import com.tpt.api_mbds.model.Fichier;
import com.tpt.api_mbds.model.Utilisateur;
import oracle.jdbc.OracleConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;





@SpringBootApplication
@CrossOrigin
@RequestMapping("/oracle")
public class ApiMbdsApplication {

    @GetMapping(path="/test",produces = "application/json")
    @ResponseBody
    public String testApi() throws SQLException {
        Utilisateur val = new Utilisateur();
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
            Utilisateur f = new Utilisateur();
            val = f.getAllUser(oracleConnection);
            System.out.println("ito" + val);

            return new Gson().toJson(val);

        } catch (Exception throwables) {
            throw throwables;

        }
    }


    public static void main(String[] args) {
       SpringApplication.run(ApiMbdsApplication.class, args);
    }



}

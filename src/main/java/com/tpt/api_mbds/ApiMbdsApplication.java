package com.tpt.api_mbds;

import com.tpt.api_mbds.model.Connexion;
import com.tpt.api_mbds.model.Fichier;
import oracle.jdbc.OracleConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class ApiMbdsApplication {

    public static void main(String[] args) {
       // SpringApplication.run(ApiMbdsApplication.class, args);
        int val=5;
        OracleConnection oracleConnection=null;
        try {
            oracleConnection = Connexion.getConnection();
            Fichier f=new Fichier();
            val=f.getDoublonTeam(oracleConnection);
            System.out.println("ito"+val);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}

package com.tpt.api_mbds.model;

import oracle.jdbc.OracleConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Fichier {
   public int getDoublonTeam(OracleConnection co) throws SQLException {

        int val = 2;
        Statement statement = null;
        try{
            statement = co.createStatement();

            ResultSet resultSet = statement.executeQuery("select count(*) from UTILISATEUR ");
            while (resultSet.next()){
                val = resultSet.getInt(1);
            }
        }
        finally{
            if(statement!=null)
                statement.close();
        }
        return val;
    }
}

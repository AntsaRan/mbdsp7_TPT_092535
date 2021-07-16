package com.tpt.api_mbds.Controller;

import com.tpt.api_mbds.model.Utilisateur;
import oracle.jdbc.OracleConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController {

    public UserController() {
    }

    public Utilisateur getAllUser(OracleConnection co) throws SQLException {

        Utilisateur val=new Utilisateur();
        Statement statement = null;
        try{

            statement = co.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from UTILISATEUR ");
            while (resultSet.next()){
                val.setId(resultSet.getInt(1));
                val.setNom(resultSet.getString(2));
                val.setPrenom(resultSet.getString(3));
                val.setDateNaissance(resultSet.getDate(4));
                val.setPseudo(resultSet.getString(5));
                val.setPwd(resultSet.getString(6));
                val.setJetons(resultSet.getInt(7));
                val.setMail(resultSet.getString(8));
            }
        }
        finally{
            if(statement!=null)
                statement.close();
        }
        return val;
    }

    public Utilisateur authentification(OracleConnection co , String mail,String password) throws SQLException {
        Utilisateur val=new Utilisateur();
        Statement statement = null;
        try {

            statement = co.createStatement();
            String requete ="select * from UTILISATEUR where mail='"+mail+"' and pwd='"+password+"'";
            ResultSet resultSet = statement.executeQuery("select * from UTILISATEUR where mail='"+mail+"' and pwd='"+password+"'");
            while (resultSet.next()){
                //System.out.println("ID AVANT " + resultSet.getInt(1));
                val.setId(resultSet.getInt(1));
                val.setNom(resultSet.getString(2));
                val.setPrenom(resultSet.getString(3));
                val.setDateNaissance(resultSet.getDate(4));
                val.setPseudo(resultSet.getString(5));
                val.setPwd(resultSet.getString(6));
                val.setJetons(resultSet.getInt(7));
                val.setMail(resultSet.getString(8));
            }
        }
        catch (Exception e){
            throw e;
        }
        finally{
            if(statement!=null)
                statement.close();
        }
        return val;

    }

}

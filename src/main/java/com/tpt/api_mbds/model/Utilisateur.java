package com.tpt.api_mbds.model;

import oracle.jdbc.OracleConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String pseudo;
    private String pwd;
    private int jetons;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getJetons() {
        return jetons;
    }

    public void setJetons(int jetons) {
        this.jetons = jetons;
    }

    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String prenom, Date dateNaissance, String pseudo, String pwd, int jetons) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.pseudo = pseudo;
        this.pwd = pwd;
        this.jetons = jetons;
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
            }
        }
        finally{
            if(statement!=null)
                statement.close();
        }
        return val;
    }

}

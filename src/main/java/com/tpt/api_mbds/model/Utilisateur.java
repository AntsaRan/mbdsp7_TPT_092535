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
    private String mail;

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String prenom, Date dateNaissance, String pseudo, String pwd, int jetons,String mail) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.pseudo = pseudo;
        this.pwd = pwd;
        this.jetons = jetons;
        this.mail = mail;
    }

    public Utilisateur(String nom, String prenom, Date dateNaissance, String pseudo, String pwd, int jetons, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.pseudo = pseudo;
        this.pwd = pwd;
        this.jetons = jetons;
        this.mail = mail;
    }
}

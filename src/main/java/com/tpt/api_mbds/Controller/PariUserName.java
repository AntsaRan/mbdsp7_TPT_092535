package com.tpt.api_mbds.Controller;

import java.util.Date;

public class PariUserName {
    private int id;
    private int idUtilisateur;
    private String nom;
    private int mise;
    private Date datepari;
    private String idMatch;
    private String matchRegle;

    public PariUserName() {
    }

    public PariUserName(int id, int idUtilisateur, String nom, int mise, Date datepari, String idMatch, String matchRegle) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.mise = mise;
        this.datepari = datepari;
        this.idMatch = idMatch;
        this.matchRegle = matchRegle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getMise() {
        return mise;
    }

    public void setMise(int mise) {
        this.mise = mise;
    }

    public Date getDatepari() {
        return datepari;
    }

    public void setDatepari(Date datepari) {
        this.datepari = datepari;
    }

    public String getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(String idMatch) {
        this.idMatch = idMatch;
    }

    public String getMatchRegle() {
        return matchRegle;
    }

    public void setMatchRegle(String matchRegle) {
        this.matchRegle = matchRegle;
    }
}

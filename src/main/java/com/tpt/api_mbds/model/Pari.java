package com.tpt.api_mbds.model;

import java.util.Date;

public class Pari {
    private int id;
    private int idUtilisateur;
    private String idMatch;
    private String matchRegle;
    private float mise;
    private Date dateParis;

    public Pari() {
    }

    public Pari(int idUtilisateur, String idMatch, String matchRegle, float mise,  Date dateParis) {
        this.idUtilisateur = idUtilisateur;
        this.idMatch = idMatch;
        this.matchRegle = matchRegle;
        this.mise = mise;
        this.dateParis=dateParis;
    }

    public Pari(int id, int idUtilisateur, String idMatch, String matchRegle, float mise, Date dateParis) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.idMatch = idMatch;
        this.matchRegle = matchRegle;
        this.mise = mise;
        this.dateParis = dateParis;
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

    public float getMise() {
        return mise;
    }

    public void setMise(float mise) {
        this.mise = mise;
    }

    public Date getDateParis() {
        return dateParis;
    }

    public void setDateParis(Date dateParis) {
        this.dateParis = dateParis;
    }
}

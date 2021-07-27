package com.tpt.api_mbds.model;

import java.util.Date;

public class MatchToInsert {
    private String idEquipe1;
    private String idEquipe2;
    private Date date;
    private String lieu;
    private String etat;
    private Integer scoreEquipe1;
    private Integer scoreEquipe2;
    private Integer cornerEquipe1;
    private Integer cornerEquipe2;
    private Integer possessionEquipe1;
    private Integer possessionEquipe2;

    public String getIdEquipe1() {
        return idEquipe1;
    }

    public void setIdEquipe1(String idEquipe1) {
        this.idEquipe1 = idEquipe1;
    }

    public String getIdEquipe2() {
        return idEquipe2;
    }

    public void setIdEquipe2(String idEquipe2) {
        this.idEquipe2 = idEquipe2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Integer getScoreEquipe1() {
        return scoreEquipe1;
    }

    public void setScoreEquipe1(Integer scoreEquipe1) {
        this.scoreEquipe1 = scoreEquipe1;
    }

    public Integer getScoreEquipe2() {
        return scoreEquipe2;
    }

    public void setScoreEquipe2(Integer scoreEquipe2) {
        this.scoreEquipe2 = scoreEquipe2;
    }

    public Integer getCornerEquipe1() {
        return cornerEquipe1;
    }

    public void setCornerEquipe1(Integer cornerEquipe1) {
        this.cornerEquipe1 = cornerEquipe1;
    }

    public Integer getCornerEquipe2() {
        return cornerEquipe2;
    }

    public void setCornerEquipe2(Integer cornerEquipe2) {
        this.cornerEquipe2 = cornerEquipe2;
    }

    public Integer getPossessionEquipe1() {
        return possessionEquipe1;
    }

    public void setPossessionEquipe1(Integer possessionEquipe1) {
        this.possessionEquipe1 = possessionEquipe1;
    }

    public Integer getPossessionEquipe2() {
        return possessionEquipe2;
    }

    public void setPossessionEquipe2(Integer possessionEquipe2) {
        this.possessionEquipe2 = possessionEquipe2;
    }

    public MatchToInsert() {
    }

    public MatchToInsert(String idEquipe1, String idEquipe2, Date date, String lieu) {
        this.idEquipe1 = idEquipe1;
        this.idEquipe2 = idEquipe2;
        this.date = date;
        this.lieu = lieu;
    }

    public MatchToInsert(String idEquipe1, String idEquipe2, Date date, String lieu, String etat, Integer scoreEquipe1, Integer scoreEquipe2, Integer cornerEquipe1, Integer cornerEquipe2, Integer possessionEquipe1, Integer possessionEquipe2) {
        this.idEquipe1 = idEquipe1;
        this.idEquipe2 = idEquipe2;
        this.date = date;
        this.lieu = lieu;
        this.etat = etat;
        this.scoreEquipe1 = scoreEquipe1;
        this.scoreEquipe2 = scoreEquipe2;
        this.cornerEquipe1 = cornerEquipe1;
        this.cornerEquipe2 = cornerEquipe2;
        this.possessionEquipe1 = possessionEquipe1;
        this.possessionEquipe2 = possessionEquipe2;
    }
}

package com.tpt.api_mbds.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="match")
public class Match {
    @Id
    private String id;

    private ObjectId idEquipe1;

    private ObjectId idEquipe2;
    private Date date;
    private String lieu;
    private String etat;
    private Integer scoreEquipe1;
    private Integer scoreEquipe2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ObjectId getIdEquipe1() {
        return idEquipe1;
    }

    public void setIdEquipe1(ObjectId idEquipe1) {
        this.idEquipe1 = idEquipe1;
    }

    public ObjectId getIdEquipe2() {
        return idEquipe2;
    }

    public void setIdEquipe2(ObjectId idEquipe2) {
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

    public Match() {

    }

    public Match(ObjectId idEquipe1, ObjectId idEquipe2, Date date, String lieu, String etat, Integer scoreEquipe1, Integer scoreEquipe2) {
        this.idEquipe1=idEquipe1;
        this.idEquipe2=idEquipe2;
        this.date = date;
        this.lieu = lieu;
        this.etat = etat;
        this.scoreEquipe1 = scoreEquipe1;
        this.scoreEquipe2 = scoreEquipe2;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", idEquipe1='" + idEquipe1 + '\'' +
                ", idEquipe2='" + idEquipe2 + '\'' +
                ", date=" + date +
                ", lieu='" + lieu + '\'' +
                ", etat='" + etat + '\'' +
                ", scoreEquipe1=" + scoreEquipe1 +
                ", scoreEquipe2=" + scoreEquipe2 +
                '}';
    }
}

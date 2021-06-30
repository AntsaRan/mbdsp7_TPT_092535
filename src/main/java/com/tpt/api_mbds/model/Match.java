package com.tpt.api_mbds.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.Date;
import java.util.List;

@Document(collection="match")
public class Match {
    @Id
    private String id;

    @DBRef
    private  Equipe equipe1;
    @DBRef
    private  Equipe equipe2;

    //private List<Equipe> equipes;

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

    public Equipe getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
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

    public Match(Equipe equipe1, Equipe equipe2, Date date, String lieu, String etat, Integer scoreEquipe1, Integer scoreEquipe2) {
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.date = date;
        this.lieu = lieu;
        this.etat = etat;
        this.scoreEquipe1 = scoreEquipe1;
        this.scoreEquipe2 = scoreEquipe2;
    }

    public Match(String id, Equipe equipe1, Equipe equipe2, Date date, String lieu, String etat, Integer scoreEquipe1, Integer scoreEquipe2) {
        this.id = id;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.date = date;
        this.lieu = lieu;
        this.etat = etat;
        this.scoreEquipe1 = scoreEquipe1;
        this.scoreEquipe2 = scoreEquipe2;
    }
}

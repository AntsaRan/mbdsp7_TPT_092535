package com.tpt.api_mbds.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="match_regle")
public class Match_regle {
    @Id
    private String id;

    private  String idMatch;

    private List<RegleCote> regles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(String idMatch) {
        this.idMatch = idMatch;
    }

    public List<RegleCote> getRegles() {
        return regles;
    }

    public void setRegles(List<RegleCote> regles) {
        this.regles = regles;
    }

    public Match_regle() {
    }

    public Match_regle(String idMatch, List<RegleCote> regles) {
        this.idMatch = idMatch;
        this.regles = regles;
    }

    public Match_regle(String id, String idMatch, List<RegleCote> regles) {
        this.id = id;
        this.idMatch = idMatch;
        this.regles = regles;
    }
}

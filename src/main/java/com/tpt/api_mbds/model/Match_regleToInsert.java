package com.tpt.api_mbds.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection="match_regle")
public class Match_regleToInsert {
    @Id
    private String id;

    private ObjectId idMatch;

    private List<RegleCote> regles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ObjectId getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(ObjectId idMatch) {
        this.idMatch = idMatch;
    }

    public List<RegleCote> getRegles() {
        return regles;
    }

    public void setRegles(List<RegleCote> regles) {
        this.regles = regles;
    }

    public Match_regleToInsert() {
    }

    public Match_regleToInsert(String id, ObjectId idMatch, List<RegleCote> regles) {
        this.id = id;
        this.idMatch = idMatch;
        this.regles = regles;
    }

    public Match_regleToInsert(ObjectId idMatch, List<RegleCote> regles) {
        this.idMatch = idMatch;
        this.regles = regles;
    }
}

package com.tpt.api_mbds.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class RegleCote {
    @DBRef
    private Regle regle;
    private Double cote;

    public Regle getRegle() {
        return regle;
    }

    public void setRegle(Regle regle) {
        this.regle = regle;
    }

    public Double getCote() {
        return cote;
    }

    public void setCote(Double cote) {
        this.cote = cote;
    }

    public RegleCote() {
    }

    public RegleCote(Regle regle, Double cote) {
        this.regle = regle;
        this.cote = cote;
    }
}

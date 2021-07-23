package com.tpt.api_mbds.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="jeton")
public class Jeton {
    @Id
    private String id;

    private Double prix;

    public Jeton() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Jeton(Double prix) {
        this.prix = prix;
    }
}

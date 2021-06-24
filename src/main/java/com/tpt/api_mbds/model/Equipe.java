package com.tpt.api_mbds.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="equipe")
public class Equipe {
    @Id
    private String id;

    private String nom;
    private String logo;

    public Equipe() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Equipe( String nom, String logo) {

        this.nom = nom;
        this.logo = logo;
    }
    @Override
    public String toString(){
        return "Equipe [id="+id+", nom="+nom+", logo"+logo+"]";
    }
}

package com.tpt.api_mbds.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="regle")
public class Regle {
    @Id
    private String id;
    private String titre;
    private String definition;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Regle() {
    }

    public Regle(String titre, String definition) {
        this.titre = titre;
        this.definition = definition;
    }

    public Regle(String id, String titre, String definition) {
        this.id = id;
        this.titre = titre;
        this.definition = definition;
    }
}

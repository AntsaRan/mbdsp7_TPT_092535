package com.example.bet.modele;

public class RequestPari {
    String idUtilisateur;
    String idMatch;
    String matchRegle;
    float mise;

    public RequestPari(String idUtilisateur, String idMatch, String matchRegle, float mise) {
        this.idUtilisateur = idUtilisateur;
        this.idMatch = idMatch;
        this.matchRegle = matchRegle;
        this.mise = mise;
    }
}

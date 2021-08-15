package com.example.bet.model;

public class RequestDevice {


    String idUtilisateur;
    String token;
    public RequestDevice(String idUtilisateur, String token) {
        this.idUtilisateur = idUtilisateur;
        this.token = token;
    }

    public RequestDevice() {
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

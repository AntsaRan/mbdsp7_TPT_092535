package com.example.bet.modele;

public class RequestUtilisateur {
    String nom;
    String prenom;
    String dateNaissance;
    String pseudo;
    String pwd;
    float jetons;
    String mail;


    public RequestUtilisateur(String nom, String prenom, String dateNaissance, String pseudo, String pwd, float jetons, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.pseudo = pseudo;
        this.pwd = pwd;
        this.jetons = jetons;
        this.mail = mail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public float getJetons() {
        return jetons;
    }

    public void setJetons(float jetons) {
        this.jetons = jetons;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

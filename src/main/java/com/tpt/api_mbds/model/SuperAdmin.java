package com.tpt.api_mbds.model;

public class SuperAdmin {

    private int id;
    private String pseudo;
    private String password;
    private String mail;

    public SuperAdmin() {
    }

    public SuperAdmin(int id, String pseudo, String password, String mail) {
        this.id = id;
        this.pseudo = pseudo;
        this.password = password;
        this.mail = mail;
    }

    public SuperAdmin(String pseudo, String password, String mail) {
        this.pseudo = pseudo;
        this.password = password;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

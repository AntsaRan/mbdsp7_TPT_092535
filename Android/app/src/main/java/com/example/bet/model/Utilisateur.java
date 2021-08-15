package com.example.bet.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Utilisateur implements Parcelable {
    @SerializedName("id")
    private  String id;
    @SerializedName("nom")
    private  String nom;
    @SerializedName("prenom")
    private  String prenom;
    @SerializedName("dateNaissance")
    private String dateNaissance;
    @SerializedName("pseudo")
    private  String pseudo;
    @SerializedName("pwd")
    private  String pwd;
    @SerializedName("jetons")
    private  float jetons;
    @SerializedName("mail")
    private  String mail;
    @SerializedName("solde")
    private  float solde;

    protected Utilisateur(Parcel in) {
        id = in.readString();
        nom = in.readString();
        prenom = in.readString();
        pseudo = in.readString();
        dateNaissance  = in.readString();
        pwd = in.readString();
        jetons = in.readFloat();
        mail = in.readString();
        solde = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(pseudo);
        dest.writeString(dateNaissance);
        dest.writeString(pwd);
        dest.writeFloat(jetons);
        dest.writeString(mail);
        dest.writeFloat(solde);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Utilisateur> CREATOR = new Creator<Utilisateur>() {
        @Override
        public Utilisateur createFromParcel(Parcel in) {
            return new Utilisateur(in);
        }

        @Override
        public Utilisateur[] newArray(int size) {
            return new Utilisateur[size];
        }
    };

    public Utilisateur() {
    }

    public Utilisateur(String id, String nom, String prenom, String dateNaissance, String pseudo, String pwd, float jetons, String mail, float solde) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.pseudo = pseudo;
        this.pwd = pwd;
        this.jetons = jetons;
        this.mail = mail;
        this.solde = solde;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
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

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }
}

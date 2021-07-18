package com.example.bet.modele;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Utilisateur implements Parcelable {
    @SerializedName("id")
    private  String id;
    @SerializedName("nom")
    private  String nom;
    @SerializedName("prenom")
    private  String prenom;
    @SerializedName("dateNaissance")
    private java.sql.Date dateNaissance;
    @SerializedName("pseudo")
    private  String pseudo;
    @SerializedName("pwd")
    private  String pwd;
    @SerializedName("jetons")
    private  int jetons;
    @SerializedName("mail")
    private  String mail;

    protected Utilisateur(Parcel in) {
        id = in.readString();
        nom = in.readString();
        prenom = in.readString();
        pseudo = in.readString();
        pwd = in.readString();
        jetons = in.readInt();
        mail = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(pseudo);
        dest.writeString(pwd);
        dest.writeInt(jetons);
        dest.writeString(mail);
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

    public Utilisateur(String id, String nom, String prenom, java.sql.Date dateNaissance, String pseudo, String pwd, int jetons, String mail) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.pseudo = pseudo;
        this.pwd = pwd;
        this.jetons = jetons;
        this.mail = mail;
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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(java.sql.Date dateNaissance) {
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

    public int getJetons() {
        return jetons;
    }

    public void setJetons(int jetons) {
        this.jetons = jetons;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

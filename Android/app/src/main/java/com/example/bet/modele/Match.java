package com.example.bet.modele;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Match implements Parcelable {
    public Match(String id, String idEquipe1, String idEquipe2, Date date, String lieu, String etat, int scoreEquipe1, int scoreEquipe2) {
        this.id = id;
        this.idEquipe1 = idEquipe1;
        this.idEquipe2 = idEquipe2;
        this.date = date;
        this.lieu = lieu;
        this.etat = etat;
        this.scoreEquipe1 = scoreEquipe1;
        this.scoreEquipe2 = scoreEquipe2;
    }

    public Match() {
    }

    protected Match(Parcel in) {
        id = in.readString();
        idEquipe1 = in.readString();
        idEquipe2 = in.readString();
        lieu = in.readString();
        etat = in.readString();
        scoreEquipe1 = in.readInt();
        scoreEquipe2 = in.readInt();
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEquipe1() {
        return idEquipe1;
    }

    public void setIdEquipe1(String idEquipe1) {
        this.idEquipe1 = idEquipe1;
    }

    public String getIdEquipe2() {
        return idEquipe2;
    }

    public void setIdEquipe2(String idEquipe2) {
        this.idEquipe2 = idEquipe2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getScoreEquipe1() {
        return scoreEquipe1;
    }

    public void setScoreEquipe1(int scoreEquipe1) {
        this.scoreEquipe1 = scoreEquipe1;
    }

    public int getScoreEquipe2() {
        return scoreEquipe2;
    }

    public void setScoreEquipe2(int scoreEquipe2) {
        this.scoreEquipe2 = scoreEquipe2;
    }

    @SerializedName("id")
    private  String id;
    @SerializedName("idEquipe1")
    private  String idEquipe1;
    @SerializedName("idEquipe2")
    private  String idEquipe2;
    @SerializedName("date")
    private Date date;
    @SerializedName("lieu")
    private  String lieu;
    @SerializedName("etat")
    private  String etat;
    @SerializedName("scoreEquipe1")
    private  int scoreEquipe1;
    @SerializedName("scoreEquipe2")
    private  int scoreEquipe2;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(idEquipe1);
        parcel.writeString(idEquipe2);
        parcel.writeString(lieu);
        parcel.writeString(etat);
        parcel.writeInt(scoreEquipe1);
        parcel.writeInt(scoreEquipe2);
    }
}

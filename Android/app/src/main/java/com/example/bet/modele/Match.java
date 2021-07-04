package com.example.bet.modele;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Match implements Parcelable {


    public Match() {
    }

    public Match(String id, Equipe equipe1, Equipe equipe2, Date date, String lieu, String etat, int scoreEquipe1, int scoreEquipe2, int cornerEquipe1, int cornerEquipe2, int possessionEquipe1, int possessionEquipe2) {
        this.id = id;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.date = date;
        this.lieu = lieu;
        this.etat = etat;
        this.scoreEquipe1 = scoreEquipe1;
        this.scoreEquipe2 = scoreEquipe2;
        this.cornerEquipe1 = cornerEquipe1;
        this.cornerEquipe2 = cornerEquipe2;
        this.possessionEquipe1 = possessionEquipe1;
        this.possessionEquipe2 = possessionEquipe2;
    }


    protected Match(Parcel in) {
        id = in.readString();
        equipe1 = in.readParcelable(Equipe.class.getClassLoader());
        equipe2 = in.readParcelable(Equipe.class.getClassLoader());
        lieu = in.readString();
        etat = in.readString();
        scoreEquipe1 = in.readInt();
        scoreEquipe2 = in.readInt();
        cornerEquipe1 = in.readInt();
        cornerEquipe2 = in.readInt();
        possessionEquipe1 = in.readInt();
        possessionEquipe2 = in.readInt();
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

    public Equipe getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
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

    public int getCornerEquipe1() {
        return cornerEquipe1;
    }

    public void setCornerEquipe1(int cornerEquipe1) {
        this.cornerEquipe1 = cornerEquipe1;
    }

    public int getCornerEquipe2() {
        return cornerEquipe2;
    }

    public void setCornerEquipe2(int cornerEquipe2) {
        this.cornerEquipe2 = cornerEquipe2;
    }

    public int getPossessionEquipe1() {
        return possessionEquipe1;
    }

    public void setPossessionEquipe1(int possessionEquipe1) {
        this.possessionEquipe1 = possessionEquipe1;
    }

    public int getPossessionEquipe2() {
        return possessionEquipe2;
    }

    public void setPossessionEquipe2(int possessionEquipe2) {
        this.possessionEquipe2 = possessionEquipe2;
    }

    @SerializedName("id")
    private  String id;
    @SerializedName("equipe1")
    private  Equipe equipe1;
    @SerializedName("equipe2")
    private  Equipe equipe2;
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
    @SerializedName("cornerEquipe1")
    private  int cornerEquipe1;
    @SerializedName("cornerEquipe2")
    private  int cornerEquipe2;
    @SerializedName("possessionEquipe1")
    private  int possessionEquipe1;
    @SerializedName("possessionEquipe2")
    private  int possessionEquipe2;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeParcelable(equipe1, i);
        parcel.writeParcelable(equipe2, i);
        parcel.writeString(lieu);
        parcel.writeString(etat);
        parcel.writeInt(scoreEquipe1);
        parcel.writeInt(scoreEquipe2);
        parcel.writeInt(cornerEquipe1);
        parcel.writeInt(cornerEquipe2);
        parcel.writeInt(possessionEquipe1);
        parcel.writeInt(possessionEquipe2);
    }
}

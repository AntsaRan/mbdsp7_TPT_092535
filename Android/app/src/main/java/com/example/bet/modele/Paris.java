package com.example.bet.modele;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Paris implements Parcelable {
    @SerializedName("id")
    private  String id;
    @SerializedName("idUtilisateur")
    private  String idUtilisateur;
    @SerializedName("idMatch")
    private  String idMatch;
    @SerializedName("matchRegle")
    private  String matchRegle;
    @SerializedName("mise")
    private  float mise;
    @SerializedName("dateParis")
    private String dateParis;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(String idMatch) {
        this.idMatch = idMatch;
    }

    public String getMatchRegle() {
        return matchRegle;
    }

    public void setMatchRegle(String matchRegle) {
        this.matchRegle = matchRegle;
    }

    public float getMise() {
        return mise;
    }

    public void setMise(float mise) {
        this.mise = mise;
    }

    public String getDateParis() {
        return dateParis;
    }

    public void setDateParis(String dateParis) {
        this.dateParis = dateParis;
    }

    public Paris(String id, String idUtilisateur, String idMatch, String matchRegle, float mise, String dateParis) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.idMatch = idMatch;
        this.matchRegle = matchRegle;
        this.mise = mise;
        this.dateParis = dateParis;
    }

    protected Paris(Parcel in) {
        id = in.readString();
        idUtilisateur = in.readString();
        idMatch = in.readString();
        matchRegle = in.readString();
        mise = in.readFloat();
        dateParis = in.readString();
    }

    public static final Creator<Paris> CREATOR = new Creator<Paris>() {
        @Override
        public Paris createFromParcel(Parcel in) {
            return new Paris(in);
        }

        @Override
        public Paris[] newArray(int size) {
            return new Paris[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(idUtilisateur);
        parcel.writeString(idMatch);
        parcel.writeString(matchRegle);
        parcel.writeFloat(mise);
        parcel.writeString(dateParis);
    }
}

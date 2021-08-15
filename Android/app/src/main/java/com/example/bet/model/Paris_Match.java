package com.example.bet.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Paris_Match implements Parcelable {
    @SerializedName("id")
    private  String id;
    @SerializedName("idUtilisateur")
    private  String idUtilisateur;
    @SerializedName("match")
    private  Match match;
    @SerializedName("regle")
    private  Regle regle;
    @SerializedName("mise")
    private  float mise;
    @SerializedName("dateParis")
    private String dateParis;

    public Paris_Match() {
    }

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

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Regle getRegle() {
        return regle;
    }

    public void setRegle(Regle regle) {
        this.regle = regle;
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

    public Paris_Match(String id, String idUtilisateur, Match match, Regle regle, float mise, String dateParis) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.match = match;
        this.regle = regle;
        this.mise = mise;
        this.dateParis = dateParis;
    }

    protected Paris_Match(Parcel in) {
        id = in.readString();
        idUtilisateur = in.readString();
        match = in.readParcelable(Match.class.getClassLoader());
        regle = in.readParcelable(Regle.class.getClassLoader());
        mise = in.readFloat();
        dateParis = in.readString();
    }

    public static final Creator<Paris_Match> CREATOR = new Creator<Paris_Match>() {
        @Override
        public Paris_Match createFromParcel(Parcel in) {
            return new Paris_Match(in);
        }

        @Override
        public Paris_Match[] newArray(int size) {
            return new Paris_Match[size];
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
        parcel.writeParcelable(match, i);
        parcel.writeParcelable(regle, i);
        parcel.writeFloat(mise);
        parcel.writeString(dateParis);
    }
}

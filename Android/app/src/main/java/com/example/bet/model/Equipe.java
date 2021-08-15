package com.example.bet.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Equipe implements Parcelable {
    @SerializedName("id")
    private  String id;
    @SerializedName("nom")
    private  String nom;
    @SerializedName("logo")
    private  String logo;

    public Equipe(String id, String nom, String logo) {
        this.id = id;
        this.nom = nom;
        this.logo = logo;
    }

    protected Equipe(Parcel in) {
        id = in.readString();
        nom = in.readString();
        logo = in.readString();
    }

    public static final Creator<Equipe> CREATOR = new Creator<Equipe>() {
        @Override
        public Equipe createFromParcel(Parcel in) {
            return new Equipe(in);
        }

        @Override
        public Equipe[] newArray(int size) {
            return new Equipe[size];
        }
    };

    public Equipe() {

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(nom);
        parcel.writeString(logo);
    }
}

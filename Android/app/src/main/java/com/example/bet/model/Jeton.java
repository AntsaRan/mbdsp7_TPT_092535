package com.example.bet.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Jeton implements Parcelable {
    @SerializedName("id")
    private  String id;
    @SerializedName("prix")
    private  float prix;


    protected Jeton(Parcel in) {
        id = in.readString();
        prix = in.readFloat();
    }

    public static final Creator<Jeton> CREATOR = new Creator<Jeton>() {
        @Override
        public Jeton createFromParcel(Parcel in) {
            return new Jeton(in);
        }

        @Override
        public Jeton[] newArray(int size) {
            return new Jeton[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeFloat(prix);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}

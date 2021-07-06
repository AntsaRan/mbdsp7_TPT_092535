package com.example.bet.modele;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Regle implements Parcelable {
    @SerializedName("id")
    private  String id;
    @SerializedName("titre")
    private  String titre;
    @SerializedName("definition")
    private  String definition;

    public Regle() {
    }

    public Regle(String id, String titre, String definition) {
        this.id = id;
        this.titre = titre;
        this.definition = definition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    protected Regle(Parcel in) {
        id = in.readString();
        titre = in.readString();
        definition = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(titre);
        dest.writeString(definition);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Regle> CREATOR = new Creator<Regle>() {
        @Override
        public Regle createFromParcel(Parcel in) {
            return new Regle(in);
        }

        @Override
        public Regle[] newArray(int size) {
            return new Regle[size];
        }
    };
}

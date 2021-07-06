package com.example.bet.modele;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatchRegle implements Parcelable {

    @SerializedName("id")
    private  String id;
    @SerializedName("idMatch")
    private  String idMatch;
    @SerializedName("regles")
    private List<Regle_Cote> regles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(String idMatch) {
        this.idMatch = idMatch;
    }

    public List<Regle_Cote> getRegles() {
        return regles;
    }

    public void setRegles(List<Regle_Cote> regles) {
        this.regles = regles;
    }

    public MatchRegle() {
    }

    public MatchRegle(String id, String idMatch, List<Regle_Cote> regles) {
        this.id = id;
        this.idMatch = idMatch;
        this.regles = regles;
    }

    protected MatchRegle(Parcel in) {
        id = in.readString();
        idMatch = in.readString();
        regles = in.createTypedArrayList(Regle_Cote.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(idMatch);
        dest.writeTypedList(regles);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MatchRegle> CREATOR = new Creator<MatchRegle>() {
        @Override
        public MatchRegle createFromParcel(Parcel in) {
            return new MatchRegle(in);
        }

        @Override
        public MatchRegle[] newArray(int size) {
            return new MatchRegle[size];
        }
    };
}

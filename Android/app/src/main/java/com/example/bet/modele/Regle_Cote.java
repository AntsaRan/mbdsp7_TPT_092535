package com.example.bet.modele;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Regle_Cote implements Parcelable {

    @SerializedName("regle")
    private  Regle regle;
    @SerializedName("cote")
    private  int cote;

    public Regle_Cote() {
    }

    public Regle_Cote(Regle regle, int cote) {
        this.regle = regle;
        this.cote = cote;
    }

    public Regle getRegle() {
        return regle;
    }

    public void setRegle(Regle regle) {
        this.regle = regle;
    }

    public int getCote() {
        return cote;
    }

    public void setCote(int cote) {
        this.cote = cote;
    }

    protected Regle_Cote(Parcel in) {
        regle = in.readParcelable(Regle.class.getClassLoader());
        cote = in.readInt();
    }

    public static final Creator<Regle_Cote> CREATOR = new Creator<Regle_Cote>() {
        @Override
        public Regle_Cote createFromParcel(Parcel in) {
            return new Regle_Cote(in);
        }

        @Override
        public Regle_Cote[] newArray(int size) {
            return new Regle_Cote[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(regle, i);
        parcel.writeInt(cote);
    }
}

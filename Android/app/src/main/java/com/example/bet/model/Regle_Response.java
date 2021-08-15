package com.example.bet.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Regle_Response implements Parcelable{
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Regle> results;

    public Regle_Response() {
    }

    public Regle_Response(int page, List<Regle> results) {
        this.page = page;
        this.results = results;
    }

    protected Regle_Response(Parcel in) {
        page = in.readInt();
        results = in.createTypedArrayList(Regle.CREATOR);
    }

    public static final Parcelable.Creator<Regle_Response> CREATOR = new Parcelable.Creator<Regle_Response>() {
        @Override
        public Regle_Response createFromParcel(Parcel in) {
            return new Regle_Response(in);
        }

        @Override
        public Regle_Response[] newArray(int size) {
            return new Regle_Response[size];
        }
    };

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Regle> getResults() {
        return results;
    }

    public void setResults(List<Regle> results) {
        this.results = results;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(page);
        parcel.writeTypedList(results);
    }
}

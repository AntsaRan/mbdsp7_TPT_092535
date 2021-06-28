package com.example.bet.modele;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Equipe_Response implements Parcelable {

    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Equipe> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Equipe> getResults() {
        return results;
    }

    public void setResults(List<Equipe> results) {
        this.results = results;
    }

    public Equipe_Response() {
    }

    public Equipe_Response(int page, List<Equipe> results) {
        this.page = page;
        this.results = results;
    }

    protected Equipe_Response(Parcel in) {
        page = in.readInt();
        results = in.createTypedArrayList(Equipe.CREATOR);
    }

    public static final Creator<Equipe_Response> CREATOR = new Creator<Equipe_Response>() {
        @Override
        public Equipe_Response createFromParcel(Parcel in) {
            return new Equipe_Response(in);
        }

        @Override
        public Equipe_Response[] newArray(int size) {
            return new Equipe_Response[size];
        }
    };

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

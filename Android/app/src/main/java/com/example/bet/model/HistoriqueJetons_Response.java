package com.example.bet.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoriqueJetons_Response implements Parcelable {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<HistoriqueJetons> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<HistoriqueJetons> getResults() {
        return results;
    }

    public void setResults(List<HistoriqueJetons> results) {
        this.results = results;
    }

    public HistoriqueJetons_Response() {
    }

    public HistoriqueJetons_Response(int page, List<HistoriqueJetons> results) {
        this.page = page;
        this.results = results;
    }

    protected HistoriqueJetons_Response(Parcel in) {
        page = in.readInt();
        results = in.createTypedArrayList(HistoriqueJetons.CREATOR);
    }

    public static final Parcelable.Creator<HistoriqueJetons_Response> CREATOR = new Parcelable.Creator<HistoriqueJetons_Response>() {
        @Override
        public HistoriqueJetons_Response createFromParcel(Parcel in) {
            return new HistoriqueJetons_Response(in);
        }

        @Override
        public HistoriqueJetons_Response[] newArray(int size) {
            return new HistoriqueJetons_Response[size];
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

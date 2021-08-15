package com.example.bet.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Match_Response implements Parcelable {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Match> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Match> getResults() {
        return results;
    }

    public void setResults(List<Match> results) {
        this.results = results;
    }

    public Match_Response() {
    }

    public Match_Response(int page, List<Match> results) {
        this.page = page;
        this.results = results;
    }

    protected Match_Response(Parcel in) {
        page = in.readInt();
        results = in.createTypedArrayList(Match.CREATOR);
    }

    public static final Parcelable.Creator<Match_Response> CREATOR = new Parcelable.Creator<Match_Response>() {
        @Override
        public Match_Response createFromParcel(Parcel in) {
            return new Match_Response(in);
        }

        @Override
        public Match_Response[] newArray(int size) {
            return new Match_Response[size];
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

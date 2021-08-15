package com.example.bet.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatchRegle_Response implements Parcelable {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<MatchRegle> results;

    public MatchRegle_Response() {
    }

    public MatchRegle_Response(int page, List<MatchRegle> results) {
        this.page = page;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MatchRegle> getResults() {
        return results;
    }

    public void setResults(List<MatchRegle> results) {
        this.results = results;
    }

    protected MatchRegle_Response(Parcel in) {
        page = in.readInt();
        results = in.createTypedArrayList(MatchRegle.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeTypedList(results);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MatchRegle_Response> CREATOR = new Creator<MatchRegle_Response>() {
        @Override
        public MatchRegle_Response createFromParcel(Parcel in) {
            return new MatchRegle_Response(in);
        }

        @Override
        public MatchRegle_Response[] newArray(int size) {
            return new MatchRegle_Response[size];
        }
    };
}

package com.example.bet.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Paris_Response implements Parcelable{
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Paris> results;

    public Paris_Response() {
    }

    public Paris_Response(int page, List<Paris> results) {
        this.page = page;
        this.results = results;
    }

    protected Paris_Response(Parcel in) {
        page = in.readInt();
        results = in.createTypedArrayList(Paris.CREATOR);
    }

    public static final Creator<Paris_Response> CREATOR = new Creator<Paris_Response>() {
        @Override
        public Paris_Response createFromParcel(Parcel in) {
            return new Paris_Response(in);
        }

        @Override
        public Paris_Response[] newArray(int size) {
            return new Paris_Response[size];
        }
    };

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Paris> getResults() {
        return results;
    }

    public void setResults(List<Paris> results) {
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

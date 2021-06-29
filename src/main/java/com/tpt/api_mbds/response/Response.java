package com.tpt.api_mbds.response;

import java.util.List;

public class Response<T> {
    int page;
    List<T> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Response() {
    }

    public Response(int page, List<T> results) {
        this.page = page;
        this.results = results;
    }
}

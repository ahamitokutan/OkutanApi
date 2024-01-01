package com.okutan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ExchangeRateResponse {

    @JsonProperty("data")
    private Map<String, String> data;

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ExchangeRateResponse{");
        sb.append("data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}

package com.okutan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateResponse extends BaseResponse{

    @JsonProperty("data")
    private Map<String, String> data;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ExchangeRateResponse{");
        sb.append("data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}

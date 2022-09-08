package com.marketRanking.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketDto {

    private long timestamp;
    private List<List<String>> bids = new ArrayList<>();
    private List<List<String>> asks = new ArrayList<>();
    private String ticker_id;

}

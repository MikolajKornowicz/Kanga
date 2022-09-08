package com.marketRanking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MarketRatingDto {

    private String ticker_id;
    private String base;
    private String target;

}

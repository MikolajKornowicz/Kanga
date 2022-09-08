package com.marketRanking.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class Market {

    private long timestamp;
    private List<BigDecimal> bids;
    private List<BigDecimal> asks;
    private  String ticker_id;
}

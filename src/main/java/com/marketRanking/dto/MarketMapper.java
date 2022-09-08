package com.marketRanking.dto;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarketMapper {

    public Market mapMarketDtoToMarket (final MarketDto marketDto){
        return new Market(
                marketDto.getTimestamp(),
                marketDto.getBids().stream()
                        .flatMap(Collection::stream)
                        .map(s -> BigDecimal.valueOf(Double.parseDouble(s)))
                        .collect(Collectors.toList()),
                marketDto.getAsks().stream()
                        .flatMap(Collection::stream)
                        .map(s -> BigDecimal.valueOf(Double.parseDouble(s)))
                        .collect(Collectors.toList()),
                marketDto.getTicker_id());
    }

    public List<Market> mapMarketDtoListToMarketList (final List<MarketDto> marketDtoList) {

        List<Market> marketList = new ArrayList<>();

        for (MarketDto marketDto : marketDtoList){
            marketList.add(new Market(marketDto.getTimestamp(),
                    marketDto.getBids().stream()
                            .flatMap(Collection::stream)
                            .map(s -> BigDecimal.valueOf(Double.parseDouble(s)))
                            .collect(Collectors.toList()),
                    marketDto.getAsks().stream()
                            .flatMap(Collection::stream)
                            .map(s -> BigDecimal.valueOf(Double.parseDouble(s)))
                            .collect(Collectors.toList()),
                    marketDto.getTicker_id()));
        }
        return marketList;
    }

}

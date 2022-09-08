package com.marketRanking.calculator;

import com.marketRanking.client.KangaClient;
import com.marketRanking.dto.Market;
import com.marketRanking.dto.MarketDto;
import com.marketRanking.dto.MarketMapper;
import com.marketRanking.dto.SortedMarket;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class SpreadCalculator {

    private final KangaClient kangaClient;
    private final MarketMapper mapper;

    public List<SortedMarket> marketSorter(List<Market> marketList) {

        List<SortedMarket> sortedMarkets = new ArrayList<>();


        for (Market market : marketList) {
            BigDecimal bestBid = new BigDecimal(0);
            BigDecimal bestAsk = new BigDecimal(0);
            try {
                if (market.getBids().get(0).compareTo(bestBid) > 0) {
                    bestBid = market.getBids().get(0);
                }
                if (market.getAsks().get(0).compareTo(bestAsk) > 0) {
                    bestAsk = market.getAsks().get(0);
                }
                sortedMarkets.add(new SortedMarket(market.getTicker_id(), String.valueOf(bestBid), String.valueOf(bestAsk), "-"));

            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                sortedMarkets.add(new SortedMarket(market.getTicker_id(), "-", "-", "-"));
                continue;
            }


        }
        return sortedMarkets;
    }

    public List<SortedMarket> spreadCalculator (List<Market> marketList){

        List<SortedMarket> sortedMarkets = marketSorter(marketList);

        for (SortedMarket sortedMarket : sortedMarkets){
            if(!sortedMarket.getBestBid().equals("-") && sortedMarket.getBestAsk().equals("-")){
                sortedMarket.setSpread(String.valueOf((new BigDecimal(sortedMarket.getBestAsk()).subtract(new BigDecimal(sortedMarket.getBestBid()))).subtract(new BigDecimal("0.5").multiply((new BigDecimal(sortedMarket.getBestAsk()).add(new BigDecimal(sortedMarket.getBestBid())))))));
            }
        }
        return sortedMarkets;
    }
}

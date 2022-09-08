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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
                sortedMarkets.add(new SortedMarket(market.getTicker_id(), String.valueOf(bestBid), String.valueOf(bestAsk), "-", 0));

            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                sortedMarkets.add(new SortedMarket(market.getTicker_id(), "-", "-", "-", 0));
                continue;
            }


        }
        return sortedMarkets;
    }

    public List<SortedMarket> spreadCalculator (List<Market> marketList) {

        List<SortedMarket> sortedMarkets = marketSorter(marketList);
        String spread = "/";
        List<SortedMarket> marketsWithSpread = new ArrayList<>();

        for (SortedMarket sortedMarket : sortedMarkets) {

            if (!sortedMarket.getBestBid().equals("-") && !sortedMarket.getBestAsk().equals("-")) {

                double bid = Double.parseDouble(sortedMarket.getBestBid());
                double ask = Double.parseDouble(sortedMarket.getBestAsk());

                double nominator = (ask - bid);
                double denominator = (ask + bid);
                spread = String.valueOf((nominator / (0.5 * denominator)) * 100);

                marketsWithSpread.add(new SortedMarket(sortedMarket.getTicker_id(), sortedMarket.getBestBid(), sortedMarket.getBestAsk(), spread, 0));

            } else {
                marketsWithSpread.add(new SortedMarket(sortedMarket.getTicker_id(), sortedMarket.getBestBid(), sortedMarket.getBestAsk(), "-", 0));

            }

        }
        return marketsWithSpread;
    }

    public List<SortedMarket> spreadSorter (List<Market> marketList){

        List<SortedMarket> markets = spreadCalculator(marketList);
        List<SortedMarket> sortedMarkets = new ArrayList<>();

        for (SortedMarket market : markets) {
            if (!market.getSpread().equals("-")){
                double spread = Double.parseDouble(market.getSpread());
            if (spread <= 2) {
                sortedMarkets.add(new SortedMarket(market.getTicker_id(), market.getBestBid(), market.getBestAsk(), market.getSpread(), 1));
            }
            if (spread > 2) {
                sortedMarkets.add(new SortedMarket(market.getTicker_id(), market.getBestBid(), market.getBestAsk(), market.getSpread(), 2));
            }
        }
            else {
                sortedMarkets.add(new SortedMarket(market.getTicker_id(), market.getBestBid(), market.getBestAsk(), market.getSpread(), 3));
            }
        }
        sortedMarkets = sortedMarkets.stream()
                .sorted((o1, o2) -> o1.getGroup())
                .collect(Collectors.toList());
        return sortedMarkets;

    }
}

package com.marketRanking.facade;

import com.marketRanking.calculator.SpreadCalculator;
import com.marketRanking.client.KangaClient;
import com.marketRanking.dto.MarketDto;
import com.marketRanking.dto.MarketMapper;
import com.marketRanking.dto.MarketRatingDto;
import com.marketRanking.dto.SortedMarket;
import com.marketRanking.fileCreator.FileCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketFacade {

    private final KangaClient kangaClient;
    private final MarketMapper mapper;
    private final FileCreator fileCreator;
    private final SpreadCalculator spreadCalculator;


    public List<MarketRatingDto> getPairs (){
        return kangaClient.getPairs();
    }

    public List<MarketDto> getAllMarketsData (){
        return kangaClient.getMarkets();
    }

    public void getSortedMarkets(){
        fileCreator.generateReport(spreadCalculator.spreadSorter(mapper.mapMarketDtoListToMarketList(kangaClient.getMarkets())));

    }
}

package com.marketRanking.facade;

import com.marketRanking.client.KangaClient;
import com.marketRanking.dto.MarketDto;
import com.marketRanking.dto.MarketMapper;
import com.marketRanking.dto.MarketRatingDto;
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

    public MarketDto getMarketData (){
        fileCreator.generateReport(mapper.mapMarketDtoToMarket(kangaClient.getMarket()));
        return kangaClient.getMarket();
    }

    public List<MarketRatingDto> getPairs (){
        return kangaClient.getPairs();
    }
}

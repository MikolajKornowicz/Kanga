package com.marketRanking.controller;

import com.marketRanking.dto.MarketDto;
import com.marketRanking.dto.MarketRatingDto;
import com.marketRanking.facade.MarketFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/kanga")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MarketController {

    private final MarketFacade marketFacade;

    /*
@RequestMapping(method = RequestMethod.GET, value = "/market")
    public MarketDto getData (){
    return marketFacade.getMarketData();
}

     */

@GetMapping(value = "/pairs")
    public List<MarketRatingDto> getMarketPairs (){
    return marketFacade.getPairs();
}

@GetMapping(value = "/markets")
    public List<MarketDto> getAllMarketsData(){
    return marketFacade.getAllMarketsData();
}

@GetMapping(value = "report")
    public void getReport (){
    marketFacade.getSortedMarkets();
}

}


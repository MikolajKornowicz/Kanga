package com.marketRanking.client;

import com.marketRanking.controller.MarketNotFoundException;
import com.marketRanking.dto.MarketDto;
import com.marketRanking.dto.MarketRatingDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class KangaClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(KangaClient.class);

    private final RestTemplate restTemplate;


    public MarketDto getMarket(){
        URI url = UriComponentsBuilder.fromHttpUrl("https://public.kanga.exchange/api/market/orderbook/BTC_USD")
                .build()
                .encode()
                .toUri();

        try {
            MarketDto marketResponse = restTemplate.getForObject(url, MarketDto.class);
            return Optional.ofNullable(marketResponse)
                    .orElseThrow(MarketNotFoundException::new);
        } catch (MarketNotFoundException e){
            LOGGER.error(e.getMessage(), e);
            return new MarketDto();
        }
        }

        public List<MarketRatingDto> getPairs (){
            URI url = UriComponentsBuilder.fromHttpUrl("https://public.kanga.exchange/api/market/pairs")
                    .build()
                    .encode()
                    .toUri();

            try {
                MarketRatingDto[] pairsResponse = restTemplate.getForObject(url, MarketRatingDto[].class);
                return Optional.ofNullable(pairsResponse)
                        .map(Arrays::asList).
                        orElse(Collections.emptyList())
                        .stream()
                        .filter(p -> Objects.nonNull(p.getTicker_id()))
                        .collect(Collectors.toList());
            } catch (RestClientException e){
                LOGGER.error(e.getMessage());
                return Collections.emptyList();
            }
        }

        public List<MarketDto> getMarkets (){

        List<MarketRatingDto> marketList = getPairs();
        List<MarketDto> markets = new ArrayList<>();

            for (MarketRatingDto marketRatingDto : marketList) {
                URI url = UriComponentsBuilder.fromHttpUrl("https://public.kanga.exchange/api/market/orderbook/" + marketRatingDto.getTicker_id())
                        .build()
                        .encode()
                        .toUri();

                try {
                   markets.add(restTemplate.getForObject(url, MarketDto.class));
                } catch (RestClientException e){
                    LOGGER.error(e.getMessage());
                    return Collections.emptyList();
                }
            }

            return markets;
        }
    }

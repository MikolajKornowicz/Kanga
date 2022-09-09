package com.marketRanking;


import com.marketRanking.client.KangaClient;
import com.marketRanking.controller.MarketController;
import com.marketRanking.facade.MarketFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketApp  {


    public static void main(String[] args) {

        SpringApplication.run(MarketApp.class, args);

    }
}

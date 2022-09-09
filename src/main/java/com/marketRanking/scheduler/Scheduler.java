package com.marketRanking.scheduler;

import com.marketRanking.controller.MarketController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final MarketController marketController;

    @Scheduled(fixedDelay = 60000)
    public void generateReport (){
        log.info("Generating report");
        marketController.getReport();
        log.info("Report has been generated");

    }
}

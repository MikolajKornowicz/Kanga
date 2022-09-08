package com.marketRanking.fileCreator;

import com.marketRanking.dto.Market;
import com.marketRanking.dto.MarketDto;
import com.marketRanking.dto.SortedMarket;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class FileCreator {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd'T'HH;mm;ssZ");
    GregorianCalendar gc = new GregorianCalendar();
    String dateString = sdf.format(gc.getTime());

    public void generateReport(List<SortedMarket> markets) {
        try {
            FileWriter myWriter = new FileWriter("report_spread_" + dateString + ".txt");

            myWriter.write("Spread <= 2%" + "\n" + "Nazwa rynku Spread[%]" + "\n");
            for (SortedMarket market : markets) {
                if (market.getGroup() == 1) {
                    myWriter.write(market.getTicker_id() + " " + market.getSpread() + "\n");
                }
            }

            myWriter.write("Spread <= 2%" + "\n" + "Nazwa rynku Spread[%]" + "\n");
            for (SortedMarket market : markets) {
                if (market.getGroup() == 1) {
                    myWriter.write(market.getTicker_id() + " " + market.getSpread() + "\n");
                }
            }

            myWriter.write("Spread <= 2%" + "\n" + "Nazwa rynku Spread[%]" + "\n");
            for (SortedMarket market : markets) {
                if (market.getGroup() == 3) {
                    myWriter.write(market.getTicker_id() + " " + market.getSpread() + "\n");
                }
            }
                myWriter.close();
                } catch(IOException e){
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        }

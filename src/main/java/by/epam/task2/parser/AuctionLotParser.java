package by.epam.task2.parser;

import by.epam.task2.entity.AuctionLot;
import by.epam.task2.store.AuctionLotStore;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AuctionLotParser {
    private static Logger logger = LogManager.getLogger();
    private static final String SPLITTER = "\\s";

    public void parse(List<String> strings) {
        for (String line : strings) {
            String[] splitted = line.split(SPLITTER);
            String name = splitted[0];
            double price = Double.parseDouble(splitted[1]);
            AuctionLot lot = new AuctionLot(name, price);
            if (price > 0) {
                logger.log(Level.INFO, "Lot was added to store: " + lot);
                AuctionLotStore.INSTANCE.add(lot);
            } else {
                logger.log(Level.INFO, "The following data is incorrect: " + line);
            }
        }
    }
}

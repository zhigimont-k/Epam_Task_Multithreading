package by.epam.task2.main;

import by.epam.task2.entity.AuctionLot;
import by.epam.task2.entity.AuctionParticipant;
import by.epam.task2.exception.IllegalFileInputException;
import by.epam.task2.parser.AuctionLotParser;
import by.epam.task2.reader.TextFileReader;
import by.epam.task2.store.AuctionLotStore;
import by.epam.task2.store.AuctionParticipantStore;
import by.epam.task2.validator.LineValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static Logger logger = LogManager.getLogger();
    private static final String FILE_PATH = "data/lot info.txt";
    public static final int PARTICIPANT_NUMBER = 5;

    public static void main(String[] args) {
        try {
            new AuctionLotParser().parse(new LineValidator().validate(new TextFileReader().readFile(FILE_PATH)));
        } catch (IllegalFileInputException e) {
            logger.log(Level.ERROR, e);
        }

        for (int i = 0; i < PARTICIPANT_NUMBER; i++) {
            AuctionParticipant participant = new AuctionParticipant();
            AuctionParticipantStore.INSTANCE.add(participant);
        }

        for (AuctionLot lot : AuctionLotStore.INSTANCE) {
            logger.log(Level.INFO, "Currently selling lot: " + lot);
            AuctionParticipant.setCurrentLot(lot);
            while (!lot.isSold()) {
                Thread[] threads = new Thread[PARTICIPANT_NUMBER];
                for (int i = 0; i < AuctionParticipantStore.INSTANCE.size(); i++) {
                    AuctionParticipantStore.INSTANCE.get(i).setBarrier(lot.getBarrier());
                    threads[i] = new Thread(AuctionParticipantStore.INSTANCE.get(i));
                    threads[i].start();
                }
                for (Thread thread : threads) {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        logger.log(Level.ERROR, e);
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}

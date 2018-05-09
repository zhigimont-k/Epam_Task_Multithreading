package by.epam.task2.main;

import by.epam.task2.exception.IllegalFileInputException;
import by.epam.task2.parser.AuctionLotParser;
import by.epam.task2.reader.TextFileReader;
import by.epam.task2.validator.LineValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static Logger logger = LogManager.getLogger();
    private static final String FILE_PATH = "data/lot info.txt";

    public static void main(String[] args) {
        try {
            new AuctionLotParser().parse(new LineValidator().validate(new TextFileReader().readFile(FILE_PATH)));
        } catch (IllegalFileInputException e) {
            logger.log(Level.ERROR, e);
        }
    }
}

package by.epam.task2.reader;


import by.epam.task2.exception.IllegalFileInputException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TextFileReader {
    private static Logger logger = LogManager.getLogger();

    public List<String> readFile(String fileName) throws IllegalFileInputException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IllegalFileInputException("Attempt to read non-existent file.");
        }
        if (file.length() == 0) {
            throw new IllegalFileInputException("Attempt to read empty file.");
        }
        List<String> linesList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(linesList::add);
        } catch (IOException e) {
            logger.catching(Level.FATAL, e);
            throw new RuntimeException(e);
        }
        logger.log(Level.INFO, "Lines read: " + linesList);
        return linesList;
    }
}

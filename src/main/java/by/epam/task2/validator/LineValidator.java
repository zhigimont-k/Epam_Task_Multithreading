package by.epam.task2.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class LineValidator {
    private static Logger logger = LogManager.getLogger();
    private static final String INPUT_FORMAT = "^\\S+\\s\\d+(\\.\\d*)?$";

    public List<String> validate(List<String> lines) {
        List<String> correctLines = new ArrayList<>();
        for (String line : lines) {
            if (line.matches(INPUT_FORMAT)) {
                correctLines.add(line);
            } else {
                logger.log(Level.INFO, "The following line was omitted: " + line);
            }
        }
        return correctLines;
    }
}

package by.epam.task2.exception;

public class IllegalFileInputException extends Exception {

    public IllegalFileInputException() {
    }

    public IllegalFileInputException(String message) {
        super(message);
    }

    public IllegalFileInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalFileInputException(Throwable cause) {
        super(cause);
    }
}

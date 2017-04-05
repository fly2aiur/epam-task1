package by.epam.cherednik.lines.exception;

public class LineException extends Exception {
    public LineException() {
    }

    public LineException(String message) {
        super(message);
    }

    public LineException(String message, Throwable cause) {
        super(message, cause);
    }

    public LineException(Throwable cause) {
        super(cause);
    }
}

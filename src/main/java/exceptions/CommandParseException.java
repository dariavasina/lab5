package exceptions;

public class CommandParseException extends Exception {
    public CommandParseException(String message) {
        super(message);
    }
}
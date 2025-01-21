package az.turing.userappinspringboot.exception;

public class InvalidException extends RuntimeException {
    public InvalidException(String message) {
        super(message);
    }
}

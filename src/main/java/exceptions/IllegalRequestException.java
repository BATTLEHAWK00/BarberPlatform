package exceptions;

public class IllegalRequestException extends BackendException{
    public IllegalRequestException(String message) {
        super(message, 400);
    }
    public IllegalRequestException(String message, int status) {
        super(message, status);
    }
}

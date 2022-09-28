package cn.battlehawk233.barbershop.exception;

public class IllegalRequestException extends BackendException{
    public IllegalRequestException(String message) {
        super(message, 400);
    }
    public IllegalRequestException(String message, int status) {
        super(message, status);
    }
}

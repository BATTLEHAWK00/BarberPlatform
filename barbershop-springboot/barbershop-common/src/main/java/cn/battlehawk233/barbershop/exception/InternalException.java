package cn.battlehawk233.barbershop.exception;

public class InternalException extends BackendException {
    public InternalException(String message) {
        super(message, 500);
    }

    public InternalException(String message, int status) {
        super(message, status);
    }
}

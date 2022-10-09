package cn.battlehawk233.barbershop.exception;

public class ServiceException extends BackendException {
    public ServiceException(String message) {
        super(message, 400);
    }

    public ServiceException(String message, int status) {
        super(message, status);
    }
}

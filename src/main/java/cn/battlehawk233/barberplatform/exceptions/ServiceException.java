package cn.battlehawk233.barberplatform.exceptions;

public class ServiceException extends BackendException{
    public ServiceException(String message, int status) {
        super(message, status);
    }
}

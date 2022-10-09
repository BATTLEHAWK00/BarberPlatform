package cn.battlehawk233.barbershop.exception;

public class BackendException extends RuntimeException{
    private int status;

    public BackendException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

package net.highspeedtrain.createlawandorder.core.util;

public class BadCodeException extends RuntimeException {
    
    public BadCodeException() {
        super();
    }
    
    public BadCodeException(String message) {
        super(message);
    }
    
    public BadCodeException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BadCodeException(Throwable cause) {
        super(cause);
    }
}

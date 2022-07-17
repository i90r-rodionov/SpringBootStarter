package org.example.starter.lib.idempotent.exception;

public class ConcurrentRequestException extends ModuleException{

    public ConcurrentRequestException(String message) {
        super(message);
    }

    public ConcurrentRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}

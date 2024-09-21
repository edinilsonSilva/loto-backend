package br.com.loto.exceptions;

public class PoolException extends RuntimeException {

    private final int statusCode;

    public PoolException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
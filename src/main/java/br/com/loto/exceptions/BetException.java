package br.com.loto.exceptions;

public class BetException extends RuntimeException {

    private final int statusCode;

    public BetException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
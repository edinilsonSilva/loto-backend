package br.com.loto.exceptions;

public class EfiLotoException extends RuntimeException {

    private final int statusCode;

    public EfiLotoException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
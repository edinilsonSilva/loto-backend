package br.com.loto.exceptions;

public class AccountException extends RuntimeException {

    private final int statusCode;

    public AccountException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
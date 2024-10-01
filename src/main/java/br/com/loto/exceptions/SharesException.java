package br.com.loto.exceptions;

public class SharesException extends RuntimeException {

    private final int statusCode;

    public SharesException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
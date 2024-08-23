package br.com.loto.exceptions;

import lombok.Builder;

@Builder
public class CustomResponse<T> {

    private int status;
    private String message;
    private T data;
}

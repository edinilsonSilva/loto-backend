package br.com.loto.exceptions;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomResponse<T> {

    private int status;
    private String message;
    private T content;
}

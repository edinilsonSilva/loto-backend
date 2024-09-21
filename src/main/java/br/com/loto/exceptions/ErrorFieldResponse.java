package br.com.loto.exceptions;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorFieldResponse {

	private String message;
	private String field;
	private Object parameter;
}

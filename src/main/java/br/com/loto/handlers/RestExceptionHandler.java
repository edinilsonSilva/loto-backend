package br.com.loto.handlers;

import br.com.loto.exceptions.*;
import jakarta.mail.AuthenticationFailedException;
import jakarta.mail.SendFailedException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.TransientPropertyValueException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler {

    @ExceptionHandler(TransientPropertyValueException.class)
    public ResponseEntity<?> handlerTransientPropertyValueException(final TransientPropertyValueException exception, final ServletRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .message(exception.getMessage())
                .path(((HttpServletRequest) request).getRequestURI().toString())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<?> handlerAuthenticationFailedException(final AuthenticationFailedException exception, final ServletRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .message(exception.getMessage())
                .path(((HttpServletRequest) request).getRequestURI().toString())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SendFailedException.class)
    public ResponseEntity<?> handlerSendFailedException(final SendFailedException exception, final ServletRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .message(exception.getMessage())
                .path(((HttpServletRequest) request).getRequestURI().toString())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MailSendException.class)
    public ResponseEntity<?> handlerMailSendException(final MailSendException exception, final ServletRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .message(exception.getMessage())
                .path(((HttpServletRequest) request).getRequestURI().toString())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(final MethodArgumentNotValidException exception, final ServletRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .message("Requisição possui campos inválidos")
                .path(((HttpServletRequest) request).getRequestURI().toString())
                .errors(getErrors(exception))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handlerHttpMessageNotReadableException(final HttpMessageNotReadableException exception, final ServletRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .message(exception.getMessage())
                .path(((HttpServletRequest) request).getRequestURI().toString())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<?> handlerTokenException(final TokenException exception, final ServletRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .message(exception.getMessage())
                .path(((HttpServletRequest) request).getRequestURI().toString())
                .build(), HttpStatus.BAD_REQUEST);
    }

    private List<ErrorFieldResponse> getErrors(final MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> ErrorFieldResponse.builder()
                        .message(error.getDefaultMessage())
                        .field(error.getField())
                        .parameter(error.getRejectedValue())
                        .build())
                .toList();
    }

    @ExceptionHandler(PoolException.class)
    public ResponseEntity<CustomResponse> handlePoolException(PoolException e) {
        return new ResponseEntity<>(CustomResponse.builder()
                .status(e.getStatusCode())
                .message(e.getMessage())
                .content(null)
                .build(), HttpStatus.OK);
    }

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<CustomResponse> handleAccountException(AccountException e) {
        return new ResponseEntity<>(CustomResponse.builder()
                .status(e.getStatusCode())
                .message(e.getMessage())
                .content(null)
                .build(), HttpStatus.OK);
    }

    @ExceptionHandler(EfiLotoException.class)
    public ResponseEntity<CustomResponse> handleAccountException(EfiLotoException e) {
        return new ResponseEntity<>(CustomResponse.builder()
                .status(e.getStatusCode())
                .message(e.getMessage())
                .content(null)
                .build(), HttpStatus.OK);
    }
}

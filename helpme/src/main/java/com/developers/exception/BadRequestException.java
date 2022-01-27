package com.developers.exception;
/**
 * Exception de BadRequest en el Rest - 400
 *
 */
public class BadRequestException extends RestException{
    private static final long serialVersionUID = 1L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(ErrorDto errorDto) {
        super(errorDto);
    }

    public BadRequestException(String msg) {
        super(msg);
    }
}

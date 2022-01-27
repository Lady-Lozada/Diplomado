package com.developers.exception;
/**
 * Exception de NotFound en el Rest - 404
 * 
 */
public class NotFoundException extends RestException{
    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(ErrorDto errorDto) {
        super(errorDto);
    }
}

/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway.exception;

/**
 *  Base class for all custom exceptions thrown from this module.
 * 
 * @author Borja Lopez Altarriba
 *
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public CustomException() {
        super();
    }
    
    public CustomException(final String message) {
        super(message);
    }

    public CustomException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CustomException(final Throwable throwable) {
        super(throwable);
    }
    
}

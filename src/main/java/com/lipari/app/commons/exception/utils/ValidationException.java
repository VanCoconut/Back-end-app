package com.lipari.app.commons.exception.utils;

/**
 * The type Validation exception.
 */
public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Validation exception.
     */
    public ValidationException() {
		// TODO Auto-generated constructor stub
	}

    /**
     * Instantiates a new Validation exception.
     *
     * @param message the message
     */
    public ValidationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

    /**
     * Instantiates a new Validation exception.
     *
     * @param cause the cause
     */
    public ValidationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

    /**
     * Instantiates a new Validation exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ValidationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

    /**
     * Instantiates a new Validation exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}

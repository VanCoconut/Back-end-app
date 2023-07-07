package com.lipari.app.commons.exception.utils;

/**
 * The type Data exception.
 */
public class DataException extends RuntimeException {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Data exception.
     */
    public DataException() {
	}

    /**
     * Instantiates a new Data exception.
     *
     * @param message the message
     */
    public DataException(String message) {
		super(message);
	}

    /**
     * Instantiates a new Data exception.
     *
     * @param cause the cause
     */
    public DataException(Throwable cause) {
		super(cause);
	}

    /**
     * Instantiates a new Data exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DataException(String message, Throwable cause) {
		super(message, cause);
	}

    /**
     * Instantiates a new Data exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public DataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

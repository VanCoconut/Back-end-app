package com.lipari.app.commons.exception.utils;

/**
 * The type Auth exception.
 */
public class AuthException extends RuntimeException {

    /**
     * Instantiates a new Auth exception.
     */
    public AuthException() {
		// TODO Auto-generated constructor stub
	}

    /**
     * Instantiates a new Auth exception.
     *
     * @param message the message
     */
    public AuthException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

    /**
     * Instantiates a new Auth exception.
     *
     * @param cause the cause
     */
    public AuthException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

    /**
     * Instantiates a new Auth exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public AuthException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

    /**
     * Instantiates a new Auth exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public AuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}

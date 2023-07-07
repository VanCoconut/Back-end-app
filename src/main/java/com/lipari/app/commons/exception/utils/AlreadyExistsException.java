package com.lipari.app.commons.exception.utils;

/**
 * The type Already exists exception.
 */
public class AlreadyExistsException extends RuntimeException{

    /**
     * Instantiates a new Already exists exception.
     *
     * @param message the message
     */
    public AlreadyExistsException(String message) {
        super(message);
    }
}

package com.lipari.app.commons.exception.entities;

/**
 * The type Data error response.
 */
public class DataErrorResponse {

    /**
     * The Status.
     */
    private int status;
    /**
     * The Message.
     */
    private String message;
    /**
     * The Time stamp.
     */
    private long timeStamp;

    /**
     * Instantiates a new Data error response.
     */
    public DataErrorResponse() {
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Instantiates a new Data error response.
     *
     * @param status    the status
     * @param message   the message
     * @param timeStamp the time stamp
     */
    public DataErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets time stamp.
     *
     * @return the time stamp
     */
    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets time stamp.
     *
     * @param timeStamp the time stamp
     */
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}

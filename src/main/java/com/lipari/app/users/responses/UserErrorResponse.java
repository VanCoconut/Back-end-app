package com.lipari.app.users.responses;

public class UserErrorResponse {

	private int status;
	private String massage;
	private long timeStamp;

	public UserErrorResponse() {
		// TODO Auto-generated constructor stub
	}

	public UserErrorResponse(int status, String massage, long timeStamp) {
		super();
		this.status = status;
		this.massage = massage;
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

}

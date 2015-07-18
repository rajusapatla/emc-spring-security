package com.emc.weather.model;

public class Weather {

	private int weatherId;
	private String userName;
	private String jsonServiceResponse;

	public int getWeatherId() {
		return weatherId;
	}

	public void setWeatherId(int weatherId) {
		this.weatherId = weatherId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getJsonServiceResponse() {
		return jsonServiceResponse;
	}

	public void setJsonServiceResponse(String jsonServiceResponse) {
		this.jsonServiceResponse = jsonServiceResponse;
	}

}

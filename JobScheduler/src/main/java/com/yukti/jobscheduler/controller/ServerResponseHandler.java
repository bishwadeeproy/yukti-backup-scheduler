package com.yukti.jobscheduler.controller;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ServerResponseHandler {

	public static ServerResponse getServerResponse(int responseCode, Object data) {
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setStatusCode(responseCode);
		serverResponse.setData(data);
		return serverResponse;
	}
	
	public static ServerResponse getServerResponse(int errorCode, HttpStatus httpStatus, String errorMessage, List<String> errors) {
		ApiError serverResponse = new ApiError();
		serverResponse.setStatusCode(errorCode);
		serverResponse.setErrors(errors);
		serverResponse.setMessage(errorMessage);
		return serverResponse;
	}
}

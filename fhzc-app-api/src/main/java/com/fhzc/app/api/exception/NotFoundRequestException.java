package com.fhzc.app.api.exception;

import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The request you send to server is not found")
public class NotFoundRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private ApiJsonResult result;

	public NotFoundRequestException(String message) {
		super(message);
		this.result = new ApiJsonResult();
		this.result.setCode(APIConstants.API_JSON_RESULT.NOT_FOUND);
		this.result.setMessage(message);
	}

	public ApiJsonResult getResult() {
		return result;
	}

	public void setResult(ApiJsonResult result) {
		this.result = result;
	}
}

package com.fhzc.app.api.exception;

import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The request you send to Server was bad.")
public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private ApiJsonResult result;

	public BadRequestException(String message) {
		super(message);
		this.result = new ApiJsonResult();
		this.result.setCode(APIConstants.API_JSON_RESULT.BAD_REQUEST);
		this.result.setMsg(message);
	}

	public ApiJsonResult getResult() {
		return result;
	}

	public void setResult(ApiJsonResult result) {
		this.result = result;
	}
}

package com.leafresh.backend.common.codes;

public class ResponseCode {

	private String code;
	private String message;

	public ResponseCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public ResponseCode code(String code){
		this.code = code;
		return this;
	}

	public ResponseCode message(String message){
		this.message = message;
		return this;
	}

	public ResponseCode bulider(){
		return new ResponseCode(code, message);
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}


}

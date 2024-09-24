package com.leafresh.backend.common.codes;



/**
 * [공통 코드] API 통신에 대한 에러 코드 Enum 형태로 관리를 한다.
 * Success CodeList : 성공 코드 관리
 * Success Code Constructor : 성공 코드를 사용하기 위한 생성자 구성
 */

public enum SuccessCode {

	SELECT(200, "200", "SELECT SUCCESS"),
	DELETE(200,"200", "DELETE SUCCESS"),
	SEND(200,"200", "SEND SUCCESS"),
	INSERT(201, "201", "INSERT SUCCESS"),
	UPDATE(204, "204", "UPDATE SUCCESS");

	private int status;
	private String code;
	private String message;

	SuccessCode(int status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}




}

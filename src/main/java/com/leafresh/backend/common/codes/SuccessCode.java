package com.leafresh.backend.common.codes;


import org.springframework.http.HttpStatus;

/**
 * [공통 코드] API 통신에 대한 에러 코드 Enum 형태로 관리를 한다.
 * Success CodeList : 성공 코드 관리
 * Success Code Constructor : 성공 코드를 사용하기 위한 생성자 구성
 */

public enum SuccessCode {

//	SELECT(HttpStatus.OK, 200, "SELECT SUCCESS"),
//	DELETE(HttpStatus.OK, 201, "DELETE SUCCESS"),
//	SEND(HttpStatus.OK, 201, "SEND SUCCESS"),
//	INSERT(HttpStatus.OK, 201, "INSERT SUCCESS"),
//	UPDATE(HttpStatus.OK, 201, "UPDATE SUCCESS");

	OK(HttpStatus.OK, 200, "요청에 성공했습니다."),
	USER_CREATED(HttpStatus.OK, 201, "회원가입에 성공했습니다."),
	PROFILE_CREATED(HttpStatus.OK, 201, "프로필 등록에 성공했습니다."),
	PROFILE_UPDATED(HttpStatus.OK, 201, "프로필 수정에 성공했습니다."),
	MARKET_CREATED(HttpStatus.OK, 201, "게시글 등록에 성공했습니다."),
	FEED_CREATED(HttpStatus.OK, 201, "피드 등록에 성공했습니다."),
	PLANT_CREATED(HttpStatus.OK, 201, "식물 등록에 성공했습니다."),
	PLANTCARE_CREATED(HttpStatus.OK, 201, "식물일지 등록에 성공했습니다."),
	REPLY_CREATED(HttpStatus.OK, 201, "댓글 등록에 성공했습니다."),
	TODO_CREATED(HttpStatus.OK, 201, "투두 등록에 성공했습니다.");

	private HttpStatus status;
	private int code;
	private String message;

	SuccessCode(HttpStatus status, int code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}

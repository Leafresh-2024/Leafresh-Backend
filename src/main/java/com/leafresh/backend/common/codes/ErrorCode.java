package com.leafresh.backend.common.codes;

import org.springframework.http.HttpStatus;

/**
 * Error code 전역 설정값
 *
 */
public enum ErrorCode {

	/**
	 * *********** global Error CodeList ****************
	 * Http Status Code
	 * 400 : Bad Request
	 * 401 : Unauthorized
	 * 403 : Forbidden
	 * 404 : Not Found
	 * 500 : Internal Server Error
	 * **************************************************
	 * */

	// @Reuqest Body 데이터 없는 경우
	REQUEST_BODY_MISSING_ERROR(400, HttpStatus.BAD_REQUEST,"Required Request body is Missing"),

	//Request Parameter의 데이터가 존재하지 않는 경우
	MISSING_REQUEST_PARAMETER_ERROR(400, HttpStatus.BAD_REQUEST,"Missing Servlet RequestParameter Exception"),

	// 유효하지 않은 타입
	INVALID_TYPE_VALUE(400, HttpStatus.BAD_REQUEST,"Invalid Type Value"),

	//I/O에러
	IO_ERROR(400,HttpStatus.BAD_REQUEST,"I/O Exception"),
	// 파싱 에러
	JSON_PARSE_ERROR(400, HttpStatus.BAD_REQUEST,"JsonParseException"),

	// Processing Error
	JACKSON_PROCESS_ERROR(400,HttpStatus.BAD_REQUEST,"Jackson.core Excpetion"),

	//권한 없음
	FORBIDDEN_ERROR(403, HttpStatus.FORBIDDEN,"Forbidden Excpeption"),

	// 잘못된 서버 요청
	BAD_REQUEST_ERROR(404, HttpStatus.NOT_FOUND,"Bad Request Exception"),

	//서버 리소스 없음
	NOT_FOUND_ERROR(404, HttpStatus.NOT_FOUND, "Not Found Excpetion"),

	ENTITY_NOT_FOUND_ERROR(404, HttpStatus.NOT_FOUND, "요청하신 엔티티를 찾을 수 없습니다"),
	NOT_SIGN_ID_ERROR(404, HttpStatus.NOT_FOUND,"회원가입되지 않은 계정입니다. 회원가입을 진행해주세요"),
	USER_NOT_FOUND_ERROR(404, HttpStatus.NOT_FOUND, "요청하신 유저를 찾을 수 없습니다."),
	FEED_NOT_FOUND_ERROR(404, HttpStatus.NOT_FOUND,"요청하신 피드를 찾을 수 없습니다."),
	MARKET_NOT_FOUND_ERROR(404, HttpStatus.NOT_FOUND,"요청하신 분양글을 찾을 수 없습니다."),
	PLANT_NOT_FOUND_ERROR(404, HttpStatus.NOT_FOUND,"요청하신 식물 정보를 찾을 수 없습니다."),
	PLANTCARE_NOT_FOUND_ERROR(404, HttpStatus.NOT_FOUND,"요청하신 식물일지 정보를 찾을 수 없습니다."),
	PROFILE_NOT_FOUND_ERROR(404, HttpStatus.NOT_FOUND,"요청하신 프로필 정보를 찾을 수 없습니다."),
	REPLY_NOT_FOUND_ERROR(404, HttpStatus.NOT_FOUND,"요청하신 댓글 정보를 찾을 수 없습니다."),

	// null point Exception 발생
	NULL_POINT_ERROR(404, HttpStatus.NOT_FOUND, "null point Excpetion"),

	// @RequestBody, @RequestParam, @PathVariable 값이 유효하지 않음
	NOT_VALID_ERROR(404, HttpStatus.NOT_FOUND, "hand Vaildation Exception"),

	// 서버가 처리 할 방법을 모르는 경우 발생
	INTERNAL_SERVER_ERROR(500,HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Exception"),

	UNAUTHORIZED(401, HttpStatus.UNAUTHORIZED, "접근 권한이 없습니다."), // A404
	AUTH_TOKEN_FAIL(401,HttpStatus.UNAUTHORIZED, "토큰 발급에 실패하였습니다."),            // A405
	AUTH_TOKEN_INVALID(401,HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),            // A406
	AUTH_TOKEN_NOT_MATCH(401, HttpStatus.UNAUTHORIZED, "AUTH_TOKEN_NOT_MATCH"),        // A407
	AUTH_TOKEN_IS_NULL(401, HttpStatus.UNAUTHORIZED,"AUTH_TOKEN_IS_NULL"),        // A408

	/* ******************************* Custom Error CodeList ****************************************/
	// 응답 status 수정필요(임의로 넣어둠. 맞는거 찾아서 새로 넣어야됨)

	// Business Exception Error
	BUSINESS_EXCEPTION_ERROR(200, HttpStatus.INTERNAL_SERVER_ERROR, "Business Exception Error"),

	// Transaction Insert Error
	INSERT_ERROR(200, HttpStatus.INTERNAL_SERVER_ERROR, "Insert Transaction Error Exception"),

	// Transaction Update Error
	UPDATE_ERROR(200, HttpStatus.INTERNAL_SERVER_ERROR,"Update Transaction Error Exception"),

	// Transaction Delete Error
	DELETE_ERROR(200, HttpStatus.INTERNAL_SERVER_ERROR,"Delete Transaction Error Exception"); // End


	private final int code;
	private final HttpStatus status;
	private final String message;

	ErrorCode(int code, HttpStatus status, String message) {
		this.code = code;
		this.status = status;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}

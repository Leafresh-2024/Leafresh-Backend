package com.leafresh.backend.common.response;

import com.leafresh.backend.common.codes.SuccessCode;
import org.springframework.http.ResponseEntity;

public class SuccessResponse<T> {
    private int code;
    private String message;
    private T data;

    public SuccessResponse() {
    }

    public SuccessResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseEntity<SuccessResponse> of(SuccessCode successCode) { // 상태, 코드, 메세지만 전달
        return ResponseEntity.status(successCode.getStatus())
                .body(new SuccessResponse<>(successCode.getCode(), successCode.getMessage(), null));
    }

    public static <T> ResponseEntity<SuccessResponse<T>> of(SuccessCode successCode, T data) { // 상태, 코드, 메세지, 데이터 전달
        return ResponseEntity.status(successCode.getStatus())
                .body(new SuccessResponse<>(successCode.getCode(), successCode.getMessage(), data));
    }

    public static <T> ResponseEntity<SuccessResponse<T>> of(T data) { // 데이터만 전달할 때(상태를 굳이 분류안할거라 OK로 둠)
        return ResponseEntity.status(SuccessCode.OK.getStatus())
                .body(new SuccessResponse<>(SuccessCode.OK.getCode(), SuccessCode.OK.getMessage(), data));
    }

}

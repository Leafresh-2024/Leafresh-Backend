package com.leafresh.backend.common.response;

import com.leafresh.backend.common.codes.ErrorCode;
import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private final int code;
    private final HttpStatus status;
    private final String message;

    public ErrorResponse(int code, HttpStatus status, String message) {
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

    private ErrorResponse(Builder builder) {
        this.code = builder.code;
        this.status = builder.status;
        this.message = builder.message;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new Builder()
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build();
    }

    private static class Builder {
        private int code;
        private HttpStatus status;
        private String message;

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponse build(){
            return new ErrorResponse(this);
        }
    }
}

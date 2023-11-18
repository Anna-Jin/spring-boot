package com.example.springboot._common.exception;


import com.example.springboot._common.exception.errorCode.ResponseCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final ResponseCode code;
    private final String message;

    /* 커스텀 에러 메세지 작성 시 사용 */
    public ApiException(ResponseCode code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /* 디폴트 에러 메세지 작성 시 사용 */
    public ApiException(ResponseCode code) {
        super(code.getMessage());
        this.code = code;
        this.message = code.getMessage();
    }
}

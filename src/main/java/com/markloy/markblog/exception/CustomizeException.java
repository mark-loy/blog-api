package com.markloy.markblog.exception;

import com.markloy.markblog.enums.CustomizeErrorCode;

public class CustomizeException extends RuntimeException {

    private final Integer code;
    private final String message;

    public CustomizeException(CustomizeErrorCode errorCod) {
        this.code = errorCod.getCode();
        this.message = errorCod.getMessage();
    }


    @Override
    public String getMessage(){
            return message;
    }

    public Integer getCode() {
        return code;
    }
}

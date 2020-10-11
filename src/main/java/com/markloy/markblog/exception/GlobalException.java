package com.markloy.markblog.exception;

import com.markloy.markblog.dto.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultDTO exceptionHandler(MethodArgumentNotValidException ex) {
        log.info("实体参数校验异常------{}", ex.getMessage());
        ObjectError objectError = ex.getBindingResult().getAllErrors().stream().findFirst().get();
        return ResultDTO.fail(objectError.getDefaultMessage());
    }

    @ExceptionHandler(CustomizeException.class)
    public ResultDTO exceptionHandler(CustomizeException ex) {
        log.info("自定义异常------{}", ex.getMessage());
        return ResultDTO.fail(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultDTO exceptionHandler(RuntimeException ex) {
        log.info("运行时异常------{}", ex.getMessage());
        return ResultDTO.fail(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultDTO exceptionHandler(Exception ex) {
        log.info("Exception异常------{}", ex.getMessage());
        return ResultDTO.fail(ex.getMessage());
    }

}

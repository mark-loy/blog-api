package com.markloy.markblog.exception;

import com.markloy.markblog.dto.ResultDTO;
import com.mysql.cj.exceptions.AssertionFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.UnexpectedTypeException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

@Slf4j
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResultDTO exceptionHandler(Exception ex) {
        log.info("Exception异常------{}", ex.getMessage());
        return ResultDTO.fail(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultDTO exceptionHandler(RuntimeException ex) {
        log.info("运行时异常------{}", ex.getMessage());
        return ResultDTO.fail(ex.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    public ResultDTO exceptionHandler(SQLException ex) {
        log.info("sql异常------{}", ex.getMessage());
        String message = ex.getMessage();
        return ResultDTO.fail(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultDTO exceptionHandler(MethodArgumentNotValidException ex) {
        log.info("实体参数校验异常------{}", ex.getMessage());
        ObjectError objectError = ex.getBindingResult().getAllErrors().stream().findFirst().get();
        return ResultDTO.fail(objectError.getDefaultMessage());
    }
    @ExceptionHandler(UnexpectedTypeException.class)
    public ResultDTO exceptionHandler(UnexpectedTypeException ex) {
        log.info("实体参数类型校验异常------{}", ex.getMessage());

        return ResultDTO.fail(ex.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public ResultDTO exceptionHandler(IOException ex) {
        log.info("io异常------{}", ex.getMessage());
        return ResultDTO.fail(ex.getMessage());
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public ResultDTO exceptionHandler(ClassNotFoundException ex) {
        log.info("class未找到------{}", ex.getMessage());
        return ResultDTO.fail(ex.getMessage());
    }

    @ExceptionHandler(AssertionError.class)
    public ResultDTO exceptionHandler(AssertionError ex) {
        log.info("断言异常------{}", ex.getMessage());
        return ResultDTO.fail(ex.getMessage());
    }


}

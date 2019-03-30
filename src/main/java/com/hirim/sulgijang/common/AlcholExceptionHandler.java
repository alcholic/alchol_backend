package com.hirim.sulgijang.common;

import com.hirim.sulgijang.models.response.CommonResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class AlcholExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlcholException.class)
    public final CommonResponse  handleUserNotFoundException(AlcholException ex) {
        return CommonResponse.fail(ex.getMessage());
    }
}

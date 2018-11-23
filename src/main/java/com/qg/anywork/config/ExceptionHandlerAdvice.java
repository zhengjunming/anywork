package com.qg.anywork.config;

import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.exception.AbstractAnyWorkException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create by ming on 18-8-5 上午9:56
 * <p>
 * 统一异常处理
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {

    @ExceptionHandler(AbstractAnyWorkException.class)
    public RequestResult handleException(AbstractAnyWorkException e) {
        return new RequestResult<>(e.getStatEnum(), null);
    }
}

package com.gmy.config;

import com.gmy.config.exception.BusException;
import com.gmy.config.exception.BusExceptionEnum;
import com.gmy.utils.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 * @author Guo Mao Yang
 * @date 2019/11/13 13:35
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusException.class)
    @ResponseBody
    public ResultBody busException(BusException e){
        log.error(e.getMessage(),e);
        //可以增加其他处理逻辑
        return ResultBody.error(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultBody exception(Exception e){
        log.error(e.getMessage(),e);
        return ResultBody.error(new BusException(BusExceptionEnum.OTHER_ERROR, "未知异常！请联系管理员"));
    }

}

package com.gmy.config.exception;

/**
 * 自定义异常
 * @author Guo Mao Yang
 * @date 2019/11/13 13:14
 */
public class BusException extends RuntimeException {
    private int code;
    private String message;

    private BusException(){

    }
    public BusException(String message){
        this.code = 500;
        this.message = message;
    }

    public BusException(BusExceptionEnum exceptionEnum,String message){
        this.code = exceptionEnum.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

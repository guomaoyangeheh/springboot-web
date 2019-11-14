package com.gmy.utils;

import com.gmy.config.exception.BusException;
import lombok.Data;

/**
 * 统一返回对象
 * @author Guo Mao Yang
 * @date 2019/11/13 11:30
 */
@Data
public class ResultBody {

    private boolean isok;
    private int code;
    private String msg;
    private Object data;

    private ResultBody(){

    }

    public static ResultBody error(BusException e){
        ResultBody result = new ResultBody();
        result.setIsok(false);
        result.setCode(e.getCode());
        result.setMsg(e.getMessage());
        result.setData(null);
        return result;
    }

    public static ResultBody success(Object data){
        ResultBody result = new ResultBody();
        result.setIsok(true);
        result.setCode(0);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }
    public static ResultBody success(){
        ResultBody result = new ResultBody();
        result.setIsok(true);
        result.setCode(0);
        result.setMsg("成功");
        result.setData(null);
        return result;
    }

}

package com.gmy.bean;

import lombok.Data;

/**
 * @author Guo Mao Yang
 * @date 2019/9/27 9:35
 */
@Data
public class UrlBean {
    public UrlBean(int code, String url, String msg) {
        this.code = code;
        this.url = url;
        this.msg = msg;
    }

    private int code;
    private String url;
    private String msg;
}

package com.dl.ll;




public class MessageResult {

    /***/
    private static final long serialVersionUID = -8353062568117527530L;
    private String code;//0 成功  -1 失败
    private String message;//信息
    private Object data;


    public MessageResult() {
    }

    public MessageResult(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public MessageResult(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

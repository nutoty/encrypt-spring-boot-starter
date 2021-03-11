package com.github.nutoty.encryptspringbootstarter.utils;

public class Message {

    private Integer status;
    private String msg;
    private Object obj;

    public static Message build(){
        return new Message();
    }

    public static Message ok(String msg){
        return new Message(200,msg,null);
    }

    public static Message ok(String msg,Object obj){
        return new Message(200,msg,obj);
    }

    public static Message error(String msg){
        return new Message(500,msg,null);
    }

    public static Message error(String msg,Object obj){
        return new Message(500,msg,obj);
    }

    public Message() {
    }

    public Message(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public Message setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Message setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public Message setObj(Object obj) {
        this.obj = obj;
        return this;
    }
}

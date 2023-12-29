package com.example.wlw.util;

public class HttpMessage<T> {
    public boolean messagestate;
    public T message;
    public String token;
    public HttpMessage() {
        this.messagestate = false;
    }

    public void setstate(boolean a){
        messagestate = a;
    }
    public void setMessage(T str){
        message = str;
    }
}

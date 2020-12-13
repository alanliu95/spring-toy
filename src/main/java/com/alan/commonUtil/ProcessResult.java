package com.alan.commonUtil;

public class ProcessResult<T> {
    String msg;
    T result;
    int code;
    public static <T> ProcessResult<T> success(T t){
        return new ProcessResult<>(null,0,t);
    }
    public static <T> ProcessResult<T> fail(String s){
        return new ProcessResult<>(s,-1,null);
    }
    public ProcessResult(String msg,  int code,T result) {
        this.msg = msg;
        this.result = result;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getResult() {
        return result;
    }
}

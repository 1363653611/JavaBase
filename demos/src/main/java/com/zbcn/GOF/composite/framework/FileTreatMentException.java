package com.zbcn.GOF.composite.framework;

/**
 *  @title FileTreatMentException
 *  @Description and 方法抛出异常
 *  @author zbcn8
 *  @Date 2020/6/9 18:55
 */
public class FileTreatMentException extends RuntimeException {

    public FileTreatMentException() {
    }

    public FileTreatMentException(String msg){
        super(msg);
    }
}

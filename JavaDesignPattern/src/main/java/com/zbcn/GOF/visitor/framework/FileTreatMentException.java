package com.zbcn.GOF.visitor.framework;

/**
 *  @title FileTreatMentException
 *  @Description 自定义文件操作异常类
 *  @author zbcn8
 *  @Date 2020/6/11 9:05
 */
public class FileTreatMentException extends RuntimeException {

    public FileTreatMentException() {
    }

    public FileTreatMentException(String msg){
        super(msg);
    }
}

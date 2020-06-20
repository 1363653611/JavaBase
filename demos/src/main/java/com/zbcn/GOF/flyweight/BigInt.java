package com.zbcn.GOF.flyweight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  @title BigInt
 *  @Description 整型的数字代表类
 *  @author zbcn8
 *  @Date 2020/6/20 10:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BigInt {

    /**
     * 阿拉伯数字
     */
    private int intName;

    /**
     * 中文表示
     */
    private String cnName;

    private String smCnName;

    /**
     * 英文表示
     */
    private String enName;

    public String toString(){
        return "intName =" + intName +"  cnName=" + cnName + "  simpleCnName=" + smCnName + "   enName=" + enName;
    }

}

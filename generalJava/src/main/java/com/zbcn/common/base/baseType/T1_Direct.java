package com.zbcn.common.base.baseType;

/**
 *  直接量测试
 *  <br/>
 *  @author zbcn8
 *  @since  2021/4/1 9:40
 */
public class T1_Direct {

    /**
     * 1. 整数型的直接量默认为int类型
     * 2. 浮点型的直接量默认为double类型
     * @param args
     */
    public static void main(String[] args) {
        int a = 10;
        a = a + 100;
        System.out.println(a);
        double d = 3.14;
        System.out.println(d);
    }
}

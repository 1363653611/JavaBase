package com.zbcn.GOF.flyweight;

import com.zbcn.GOF.absFactory.factory.Factory;

/**
 *  @title Main
 *  @Description 测试享元模式
 *  @author zbcn8
 *  @Date 2020/6/20 11:06
 */
public class Main {

    public static void main(String[] args) {
        BigIntFactory instance = BigIntFactory.getInstance();
        for (int i = 0; i < 11; i++) {
            BigInt bigInt = instance.getBigInt(i);
            System.out.println(bigInt);
        }

        for (int i = 0; i < 11; i++) {
            BigInt bigInt = instance.getBigInt(i);
            System.out.println(bigInt);
        }
    }
}

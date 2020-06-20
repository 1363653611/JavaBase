package com.zbcn.GOF.flyweight;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;

/**
 *  @title BigIntFactory
 *  @Description bigInt 生成工具类
 *  @author zbcn8
 *  @Date 2020/6/20 10:35
 */
public class BigIntFactory {

    private final static String[] cnNames = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖","拾","佰","仟","万","亿"};

    private final static String[] cnSmNames = {"〇","一","二","三","四","五","六","七","八","九","十"};

    private final static String[] enNames = {"zero","one","two","three","four","five","six","seven","eight","nine","ten"};

    /**
     * 管理已生成的 bigInt 类
     */
    private Map<Integer, BigInt> pool = Maps.newHashMap();

    //单例模式
    private static BigIntFactory factory = new BigIntFactory();

    private BigIntFactory() {
    }

    public static BigIntFactory getInstance(){
        return factory;
    }

    public synchronized BigInt  getBigInt(Integer intName){
        BigInt bigInt = pool.get(intName);
        if(Objects.isNull(bigInt)){
            //生成实例
            bigInt = generatorBigInt(intName);
            pool.put(intName, bigInt);
        }
        return bigInt;
    }

    private BigInt generatorBigInt(Integer intName) {
        if (intName %1 != 0 ||intName > 10 || intName < 0){
            throw new IllegalArgumentException("数字范围是 0-10 的整数");
        }
        System.out.println("创建数字：" + intName);
        return new BigInt(intName, cnNames[intName],cnSmNames[intName],enNames[intName]);

    }
}

package com.zbcn.common.base.baseType;

/**
 * 数据转换
 */
public class T2_DataConvert {

    /**
     * 自动转换：低类型的向高类型的转换
     * 强制转换：高类型的向底类型转换，但可能会数据溢出或者精度丢失
     * @param args
     */
    public static void main(String[] args) {
        long a=200;//200是直接量，默认为int类型这里是自动转换为long类型
        ////100000000000是个整数直接量，默认应该为int类型，但是超出了int类型的取值范围
        /*long b=100000000000;*/

        //在整数直接量后面将了一个L，表示该直接量不再默认为int类型，为long类型，所以没错
        long c=1000000000000L;

        //浮点型直接量默认为double,double类型大，所以不能直接转换为float
        /*float d=34.3;*/

        //在浮点数直接量后面加上一个f，表示该直接量不再默认为double，为float类型
        float e=34.3f;
    }
}

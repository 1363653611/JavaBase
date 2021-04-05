package com.zbcn.common.base.baseType;

/**
 * int型直接量可以直接赋给byte、short、char类型变量，只要不超出变量类型的取值范围
 */
public class T3_IntAssignment {

    public static void main(String[] args) {
        byte a=100;
        short b=200;
        //注意char类型是一种特殊的int类型，可以不用写成单引号括起来的
        char c=100;

        /*直接量128超出了byte类型的取值范围*/
        // byte d=128;
        /*直接量-1不在char类型的取值范围内*/
        //char e=-1;
    }
}

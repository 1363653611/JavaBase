package com.zbcn.algorithm;

/**
 * 数值的整数次方
 * <br/>
 *
 * @author zbcn8
 * @since 2021/4/11 16:14
 */
public class T9_IntegerPower {

    public static void main(String[] args) {
        double base = 5.00d;
        int exponent = 5;
//        double power = power(base, exponent);
//        double power = power2(base, exponent);
        double power = power3(base, exponent);
        System.out.println(power);
    }

    private static double power3(double base, int exponent) {
        double result = 0.00d;
        if (equal(base, 0.00d) && exponent < 0) {
            throw new RuntimeException("无意义的值！");
        }
        if (exponent == 0) {
            return 1.0;
        } else if (exponent < 0) {
            result = powerWithExponent(1 / base, -exponent);
        }else if(exponent > 0){
            result= powerWithExponent( base, exponent);
        }
        return result;
    }

    //a^n = a^(n/2) * a^(n/2)；n为偶数；
    //a^n = a^[(n-1)/2] * a^[(n-1)/2] * a；n为奇数；
    private static double powerWithExponent(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }
        double result = powerWithExponent(base, exponent >> 1);//用右移代替了除以2
        result *= result;
        //用位与运算符代替了求余运算符（%）来判断一个数是奇数还是偶数，如果是奇数还需要再乘一个base
        if ((exponent & 0x1) == 1){
            result *= base;
        }
        return result;
    }


    /**
     * 判断double 类型的值是否相等
     *
     * @param num1
     * @param num2
     * @return
     */
    public static boolean equal(double num1, double num2) {
        if (Math.abs(num1 - num2) < 0.0000001) {
            return true;
        } else {
            return false;
        }
    }

    //方式2
    private static double power2(double base, int exponent) {
        double result = 1.0d;
        if (exponent == 0) {
            return result;
        }
        if (exponent > 0 || exponent < 0) {

            for (int i = 0; i < exponent || i < -exponent; i++) {
                result = result * base;
            }
            if (exponent < 0) {
                result = 1 / exponent;
            }
        }
        return result;

    }

    //方式1
    private static double power(double base, int exponent) {
        double result = 1.0d;
        if (exponent == 0) {
            return 0;
        } else if (exponent == 1) {
            return base;
        } else if (exponent > 1) {
            for (int i = 0; i < exponent; i++) {
                result = result * base;
            }
            return result;
        } else {
            exponent = -exponent;
            for (int i = 0; i < exponent; i++) {
                result = result * base;
            }
            result = 1 / result;
            return result;
        }
    }
}

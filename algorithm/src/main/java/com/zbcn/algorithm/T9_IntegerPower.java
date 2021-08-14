package com.zbcn.algorithm;
/**
 * 数值的整数次方
 *
 * @author likun
 * @since 2021/8/12 9:14
 */
public class T9_IntegerPower {

    public static void main(String[] args) {

        double base = 10.05D;
        int exponent = 5;
        double power = power(base, exponent);
        System.out.println(power);
        double power2 = power2(base, exponent);
        System.out.println(power2);
        double power3 = power3(base, exponent);
        System.out.println(power3);
    }


    /**
     * 传统的方式求指数1
     * @param base
     * @param exponent
     * @return
     */
    private static double power2(double base, int exponent){
        if (exponent == 0){
            return 1;
        }
        double result = 1.00d;
        if (exponent > 0 || exponent < 0){
            for (int i = 0; i < exponent || i < -exponent; i++){
                result = result * base;
            }
            if (exponent < 0){
                result = 1/ result;
            }
        }
        return result;
    }

    private static double power3(double base, int exponent){
        double result = 1.00d;
        if (exponent == 0){
            return 1;
        }else if (exponent > 0){
            for (int i = 0; i < exponent; i++){
                result = result * base;
            }
        }else if (exponent <0){
            for (int i = 0; i < -exponent; i++){
                result = result * base;
            }
            if (exponent < 0){
                result = 1/ result;
            }
        }
        return result;
    }




    /**
     * 利用公式
     * a^n = a^(n/2) * a^(n/2)；n为偶数；
     * a^n = a^[(n-1)/2] * a^[(n-1)/2] * a；n为奇数；
     * @param base
     * @param exponent
     * @return
     */
    private static double power(double base, int exponent) {
        // 当指数位负数的时候，可以对指数求绝对值，然后 算出次方结果后，取倒数。
        // 但是 0 不能取倒数，所以 当base 为0 ，exponent 为 负数时，为非法参数
        if (equals(base,0.00d) && exponent < 0){
            throw new IllegalArgumentException("无效的参数");
        }
        double result = 0.00d;
        if (exponent == 0){
            result =  1.00d;
        }else if (exponent < 0){
            //当指数位负数的时候，可以对指数求绝对值，然后 算出次方结果后，取倒数。
            result = powerWithExponent(1/base, -exponent);
        }else if (exponent > 0){
            result = powerWithExponent(base, exponent);
        }
        return result;
    }

    //a^n = a^(n/2) * a^(n/2)；n为偶数；
    //a^n = a^[(n-1)/2] * a^[(n-1)/2] * a；n为奇数；
    private static double powerWithExponent(double base, int exponent) {
        if (exponent == 0){
            return 1.00;
        }else if (exponent == 1){
            return base;
        }
        //用右移代替了除以2
        double result = powerWithExponent(base,exponent >> 1);
        result *= result;
        //用 位和 0x1 与（&） 运算符， 代替了 求余 运算（%）来判断一个数时奇数还是偶数，如果时 奇数，还得乘以一个 base
        if ((exponent & 0x1) == 1){
            result *= base;
        }
        return result;
    }


    /**
     * 判断两个数的绝对值是不是相等
     * @param num1
     * @param num2
     * @return
     */
    public static boolean equals(double num1, double num2){
        if (Math.abs(num1 - num2) < 0.0000001){
            return true;
        }
        return false;
    }
}

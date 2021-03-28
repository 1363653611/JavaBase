package com.zbcn.algorithm;
/**
 *  斐波那契数列
 *  <br/>
 *  @author zbcn8
 *  @since  2021/3/22 10:04
 */
public class FibonacciDemo {

    /**
     *  通用公式 F[n]=F[n-1]+F[n-2] (n>=2,F[0]=0,F[1]=1)
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            //int result = recursionFunc(i);
            int result = forFunc(i);
            System.out.println(result);
        }
    }

    //递归方式 (递归实现效率差，重复计算次数多)
    private static int  recursionFunc(int num){
        if(num == 0 || num == 1){
            return num;
        }
        return recursionFunc(num -1) + recursionFunc(num -2);
    }
    //非递归的方式
    private static int forFunc(int num){
        if(num == 0 || num == 1){
            return num;
        }
        int fin = 0;
        int first = 0;
        int second = 1;
        for(int i = 2; i<= num; i++){
            fin = first + second;
            System.out.println("结果:" + fin);
            first = second;
            second = fin;
        }
        return fin;
    }
}

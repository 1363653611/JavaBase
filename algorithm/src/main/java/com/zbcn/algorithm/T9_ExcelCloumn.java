package com.zbcn.algorithm;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 十进制数字用A~Z表示成二十六进制
 * 1. 在Excel中，用A表示第一列，B表示第二列...Z表示第26列，AA表示第27列，AB表示第28列...依次列推。请写出一个函数，输入用字母表示的列号编码，输出它是第几列。
 *
 * 2. 在Excel中，用第一列用A表示，第二列用B表示...第26列用Z表示，第27列用AA表示，第28列用AB表示...依次列推。请写出一个函数，输入一个数表示第几列，输出用字母表示的列号编码。
 *
 * @author likun
 * @since 2021/8/10 9:48
 */
public class T9_ExcelCloumn {

    private  static Pattern pattern = Pattern.compile("\\d+");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String str = sc.next();
            Matcher matcher = pattern.matcher(str);
            if (matcher.matches()){
                String s = decimalToErShiliu(Integer.valueOf(str));
                System.out.println("十进制 转 二十六进制：" + s);
            }else {
                int res = erShiliuToDecimal(str);
                System.out.println("二十六进制 转 十进制：" + res);
            }

        }
    }

    /**
     * 在Excel中，用A表示第一列，B表示第二列...Z表示第26列，AA表示第27列，AB表示第28列...依次列推。请写出一个函数，输入用字母表示的列号编码，输出它是第几列。
     * @param str
     * @return
     */
    private static int erShiliuToDecimal(String str) {
        char[] chars = str.toCharArray();
        int exp = 0; // 指数
        int num = 0;
        for (int i = chars.length-1; i >= 0; i--){
            num += (chars[i] - 'A' + 1)* Math.pow(26,exp);
            exp++;
        }
        return num;
    }

    /**
     * 在Excel中，用第一列用A表示，第二列用B表示...第26列用Z表示，第27列用AA表示，第28列用AB表示...依次列推。请写出一个函数，输入一个数表示第几列，输出用字母表示的列号编码。
     * @param cols
     * @return
     */
    private static String decimalToErShiliu(int cols){
        StringBuilder builder = new StringBuilder();
        while (cols != 0){
            //余数为最后一位
            int temp = cols % 26;
            //下一位
            cols = cols / 26;
            //则说明最后一位为 Z
            if (temp == 0){
                temp = 26;
                // 前一位 则 减 1
                cols --;
            }
            char result = (char) (temp + 'A' -1);
            builder.append(result);
        }
        // 因为计算是从最后一位开始的，所以要反转过来
        builder.reverse();
        return builder.toString();
    }
}

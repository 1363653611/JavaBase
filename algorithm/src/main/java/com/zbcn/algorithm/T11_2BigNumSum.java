package com.zbcn.algorithm;

/**
 * 两个大数相加问题
 *
 * @author likun
 * @since 2021/8/16 9:13
 */
public class T11_2BigNumSum {

    public static void main(String[] args) {
        String mum1= "12344111111111111";
        String num2 = "43242";
        String result = sumOf2Num(mum1, num2);
        System.out.println(result);
    }

    private static String sumOf2Num(String num1, String num2) {
        // 初始化 加数
        int[] num1Arr = init(num1);
        int[] num2Arr = init(num2);
        // 获取两个加数中最大的长度
        int maxLen = Math.max(num1.length(), num2.length());
        // 结果数值 结果数组的大小必须是最大加数个数加1（有可能进位）
        int[] sum = new int[maxLen +1];
        for (int i = 0; i <= maxLen; i++){
            sum[i] = 0;
        }
        // 需要保存的进位值
        int nTakeOver = 0;
        // 一定要是<=maxLen，否则最高位没法进位
        for (int i = 0; i <=maxLen; i++){
            int adder1 = getAdder(num1Arr, i);
            int adder2 = getAdder(num2Arr, i);
            sum[i] = adder1 + adder2 + nTakeOver;
            // 判断是不是越过了进位
            if (sum[i] > 10){
                nTakeOver = sum[i] / 10;
                sum[i] = sum[i] % 10;
            }else {
                // 无进位
                nTakeOver = 0;
            }
        }
        // 计算最高位是否需要进位
        if (0 == sum[maxLen]){
            maxLen = maxLen -1;
        }
        // 计算的结果拼接：个位在左边，所以要从大到小
        StringBuilder result = new StringBuilder();
        for(int i = maxLen;i >= 0;i--){
            result.append(sum[i]);
            //System.out.print(sum[i]);
        }
        return result.toString();

    }

    private static int getAdder(int[] numArr,int i) {
        int add = 0;
        if (i < numArr.length){
            add = numArr[i];
        }else{
            add = 0;
        }
        return add;
    }

    private static int[] init(String num) {
        int len = num.length();
        int[] result = new int[len];
        // 需要将数值倒过来，从左到 右依次为：个，十，百 ......
        for (int i = 0, j = len -1; j >= 0; j--,i++){
            // 因为字符串中保存的是ascii码，因此需要减去48,48是数字0
            result[i] = num.charAt(j) - 48;
        }
        return result;
    }
}

package com.zbcn.algorithm;

import org.apache.commons.lang3.StringUtils;

/**
 *  把字符串中的每个空格都替换成 "%20"
 *  eg: we are happy   --> we%20are%20happy
 *  <br/>
 *  @author zbcn8
 *  @since  2021/3/22 17:11
 */
public class T2_StringDemo {

    public static void main(String[] args) {
        String str = "we are happy";
        String replacer = "%20";
        //String s = replaceFunc(str, replacer);
        String s = replaceFunc2(str, replacer);
        System.out.println(s);
    }

    /**
     * 该方案存在问题：字符串中的每个字符都移动了多次，
     * @param str
     * @param replacer
     * @return
     */
    private static String replaceFunc(String str, String replacer) {
        if(StringUtils.isBlank(str)){
            return str;
        }
        //计算需要修改空格的次数
        char[] chars = str.toCharArray();
        int length = chars.length;
        int times = 0;
        for (int i = 0; i < length; i++){
            char c = chars[i];
            if(c == ' '){
                times++;
            }
        }
        if(times == 0){
            return str;
        }
        char[] replacers = replacer.toCharArray();
        char[] dest = new char[length + times*2];
        int pos = 0;
        for (int i = 0; i < chars.length; i++){
            char c = chars[i];
            if (c == ' '){
                dest[pos] = replacers[0];
                dest[++pos]= replacers[1];
                dest[++pos] = replacers[2];
            }else{
                //先使用后指针后移动
                dest[pos++] = chars[i];
            }
        }
        return new String(dest);
    }


    private static String replaceFunc2(String str, String replacer){
        if(StringUtils.isBlank(str)){
            return str;
        }
        //计算需要修改空格的次数
        char[] chars = str.toCharArray();
        int length = chars.length;
        int times = 0;
        for (int i = 0; i < length; i++){
            char c = chars[i];
            if(c == ' '){
                times++;
            }
        }
        if(times == 0){
            return str;
        }
        char[] newChars = new char[length + times*2];
        int firstPos = length-1;
        int secondPos = length-1 + times*2;
        //secondPos == firstPos 时，说明没有空格了
        while(firstPos >= 0){
            char c = chars[firstPos];
            if(c != ' '){
                newChars[secondPos--] = c;
            }else{
                newChars[secondPos] = '%';
                newChars[--secondPos] = '2';
                newChars[--secondPos] = '0';
            }
            firstPos--;
        }
        return new String(newChars);
    }
}

package com.zbcn.algorithm;

import java.util.Stack;

/**
 * 验证二叉树的前序序列化
 * 时间复杂度：O(n)，其中 nn 为字符串的长度。我们每个字符只遍历一次，同时每个字符对应的操作都是常数时间的。
 *
 * 空间复杂度：O(n)。此为栈所需要使用的空间。
 */
public class Solution {

    public static void main(String[] args) {
        String trueStr = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        //boolean validSerialization = isValidSerialization(trueStr);
        boolean validSerialization = isValidSerialization2(trueStr);
        System.out.println(validSerialization);
    }

    //栈中的每个元素，代表了对应节点处剩余槽位的数量，而栈顶元素就对应着下一步可用的槽位数量。
    // 当遇到空节点时，仅将栈顶元素减 1；当遇到非空节点时，将栈顶元素减 1 后，再向栈中压入一个 2。
    // 无论何时，如果栈顶元素变为 0，就立刻将栈顶弹出。
    //遍历结束后，若栈为空，说明没有待填充的槽位，因此是一个合法序列；否则若栈不为空，则序列不合法。此外，在遍历的过程中，若槽位数量不足，则序列不合法。
    public static boolean isValidSerialization(String preorde) {
        //二叉树字符串的长度
        int len = preorde.length();
        //存放槽位: 栈中的每个元素，代表了对应节点处剩余槽位的数量，而栈顶元素就对应着下一步可用的槽位数量
        Stack<Integer> sorts = new Stack<>();
        sorts.push(1);
        //标志元素的开始位置
        int i = 0;
        // O(n) 的时间复杂度，表示从 第 0 个元素开始往后检查
        while(i < len){
            //便利过程中，槽位不足，说明序列不合法
            if(sorts.isEmpty()){
                return false;
            }
            if(preorde.charAt(i) == ',' ){
               i++;
            }else if(preorde.charAt(i) == '#'){
                // 当遇到空节点时，仅将栈顶元素减 1;
                Integer pop = sorts.pop() - 1;
                //如果栈顶元素不为 0，将弹出的元素继续压入桟中
                if(pop > 0){
                    sorts.push(pop);
                }
                i++;
            }else {
                // 读数字，有可能为多位（867）
                while (i < len && preorde.charAt(i) != ','){
                    i++;
                }
                //当遇到非空节点时，将栈顶元素减 1 后，再向栈中压入一个 2
                Integer pop = sorts.pop() -1;
                if(pop > 0){
                    sorts.push(pop);
                }
                sorts.push(2);
            }
        }
        return sorts.isEmpty();
    }

    //如果把栈中元素看成一个整体，即所有剩余槽位的数量，也能维护槽位的变化。
    //因此，我们可以只维护一个计数器，代表栈中所有元素之和，其余的操作逻辑均可以保持不变。
    //时间复杂度：O(n)O(n)，其中 nn 为字符串的长度。我们每个字符只遍历一次，同时每个字符对应的操作都是常数时间的。
    //空间复杂度：O(1)O(1)。
    public static boolean isValidSerialization2(String preorder){
        //二叉树 字符串的长度
        int len = preorder.length();
        //槽位的个数,初始化为1
        int sorts = 1;
        //字符的开始位置
        int i = 0;
        while (i < len){
            //遍历过程中，如果 没有槽位，说明序列不合法
            if(sorts == 0){
                return false;
            }
            //如果 为  ",", 继续寻找下一个元素
            if(preorder.charAt(i) == ','){
                i++;
            }else if (preorder.charAt(i) == '#'){//如果为空元素
                sorts--;
                i++;
            }else {//如果为正常数字
                while (i < len && preorder.charAt(i) != ','){
                    i++;
                }
                //sorts = sorts-1+2;
                sorts++;
            }
        }
        //槽位已空，说明序列正常
        return sorts == 0;
    }

}

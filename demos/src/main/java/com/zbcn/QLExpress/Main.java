package com.zbcn.QLExpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.Operator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        //test1();
        //test5();
        test4();
    }

    /*************************************************Operator***************************************************/

    /**
     * 替换 if then else 等关键字
     * @throws Exception
     */
    private static void test5() throws Exception{

        ExpressRunner runner = new ExpressRunner();
        runner.addOperatorWithAlias("如果","if",null);
        runner.addOperatorWithAlias("则", "then", null);
        runner.addOperatorWithAlias("否则", "else", null);

        String express = "如果 (语文 + 数学 + 英语 > 270) 则 {return 1;} 否则 {return 0;}";
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("语文",90);
        context.put("数学",90);
        context.put("英语",80);
        Object execute = runner.execute(express, context, null, false, false, null);
        System.out.println(execute);
    }


    //如何使用Operator
    private static void test6() throws Exception{

        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        runner.addOperator("join", new JoinOperator());
        Object r = runner.execute("1 join 2 join 3", context, null, false, false);
        System.out.println(r); // 返回结果 [1, 2, 3]

//(2)replaceOperator
//        ExpressRunner runner = new ExpressRunner();
//        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
//        runner.replaceOperator("+", new JoinOperator());
//        Object r = runner.execute("1 + 2 + 3", context, null, false, false);
//        System.out.println(r); // 返回结果 [1, 2, 3]

//(3)addFunction
//        ExpressRunner runner = new ExpressRunner();
//        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
//        runner.addFunction("join", new JoinOperator());
//        Object r = runner.execute("join(1, 2, 3)", context, null, false, false);
//        System.out.println(r); // 返回结果 [1, 2, 3]
    }


    /**
     *  定义一个继承自com.ql.util.express.Operator的操作符
     */
    public static class JoinOperator extends Operator{

        @Override
        public Object executeInner(Object[] list) throws Exception {
            Object opdata1 = list[0];
            Object opdata2 = list[1];
            if (opdata1 instanceof List) {
                ((List)opdata1).add(opdata2);
                return opdata1;
            } else {
                List result = new ArrayList();
                for (Object opdata : list) {
                    result.add(opdata);
                }
                return result;
            }
        }
    }



    /**
     * if .... else ... 表达式
     * @throws Exception
     */
    private static void test4() throws Exception {

        DefaultContext<String, Object> context = new DefaultContext<>();
        ExpressRunner runner = new ExpressRunner();
        context.put("index",19);
        context.put("val",20);
        String express = " result = null;" +
                "if (index > val){ \n" +
                "                result = \"优\";\n" +
                "        }else if(index < val){\n" +
                "                result = \"良\";\n" +
                "        }else {\n" +
                "            result = \"差\";}" +
                "return result";
        //可通过timeoutMillis参数设置脚本的运行超时时间:1000ms
        Object r = runner.execute(express, context, null, true, true);
        System.out.println(r);
    }

    private static void test3() throws Exception {

            DefaultContext<String, Object> context = new DefaultContext<>();
            ExpressRunner runner = new ExpressRunner();
            String express = "sum = 0; for(i = 0; i < 100; i++) {sum = sum + i;} return sum;";
            //可通过timeoutMillis参数设置脚本的运行超时时间:1000ms
            Object r = runner.execute(express, context, null, true, false);
            System.out.println(r);
    }


    //支持 +,-,*,/,<,>,<=,>=,==,!=,<>【等同于!=】,%,mod【取模等同于%】,++,--,
    //in【类似sql】,like【sql语法】,&&,||,!,等操作符
    //支持for，break、continue、if then else 等标准的程序控制逻辑

    private static void test2() throws Exception {
        String express = "int n=10;\n" +
                "int sum = 0;" +
                "        for(int i=0;i<n;i++){\n" +
                "            sum=sum+i;\n" +
                "        }\n" +
                "        return sum;";

        // 三元逻辑运算
        express = "int a = 1;\n" +
                "int b = 2;\n" +
                "int maxnum = a > b ? a : b;";
        DefaultContext<String, Object> context = new DefaultContext<>();
        ExpressRunner runner = new ExpressRunner();
        Object execute = runner.execute(express, context, null, true, true);
        System.out.println(execute);
    }


    private static void test1() {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("WGZQ_NUM", BigDecimal.valueOf(104));
        context.put("WGZQ_HZ", BigDecimal.valueOf(43.56));
        context.put("ZLS_HZ", BigDecimal.valueOf(22));
        //下面五个参数意义分别是 表达式，上下文，errorList，是否缓存，是否输出日志
        Object result = null;
        try {
            result = runner.execute("WGZQ_NUM*(WGZQ_HZ-ZLS_HZ)/4.186/7000*10", context, null, true, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("结果：" + result);
    }
}

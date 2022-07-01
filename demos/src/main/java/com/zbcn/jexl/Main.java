package com.zbcn.jexl;

import org.apache.commons.jexl3.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        test1();

        test2();
    }

    private static void test2() {
        // 判断提交时间是否大于某一个时间点
        String expressionStr = "submitTime.getTime() >= 1583856000000";
        // 创建Context对象，为表达式中的未知数赋值
        JexlContext context = new MapContext();
        Date date = new Date();
        long time = date.getTime();
        context.set("submitTime",date);
// 判断字符串是否包含“成功”
 //       String expressionStr = "text.contains('成功')";
  //      context.set("text","请求成功");
// 判断字符串是否为空
 //       String expressionStr = "text eq null || text.size() == 0"
// 判断是否属于数组中的任意一个
//        String expressionStr = "text =~ ['请求成功','啦啦','吧啦吧啦']"
// 判断是否不属于数组中的任意一个
 //       String expressionStr = "text !~ ['请求成功','啦啦','吧啦吧啦']"
// 表达式为逻辑语句，运算结果为：2
//        String expressionStr = "if(a>b){c=a;}else{c=b};";
//        context.set("a", 1);
//        context.set("b", 2);
// 表达式为对象调用方法
//        String expressionStr = "person.getName()";
//        Person person = new Person();
//        person.setName("Sixj");
//        context.set("person", person);

        JexlEngine engine = new JexlBuilder().create();
        Object evaluate = engine.createExpression(expressionStr).evaluate(context);
        System.out.println("当前时间：" + time);
        System.out.println(evaluate);

    }

    private static void test1() {
        // 描述一个人，他有两条腿
        Map<String, Object> person=new HashMap<String, Object>();
        person.put("skinColor", "red");  // 皮肤为红色
        person.put("age", 23);   // 年龄23
        person.put("cash", 60.8);      // 身上有60.8元现金

        // 左腿定义
        Map<String, Object> leg1=new HashMap<String, Object>();
        leg1.put("leftOrRight", "left");  // 左腿
        leg1.put("length", 20.3);  // 腿长多少
        leg1.put("hair", 3000);  //有多少腿毛

        // 右腿定义
        Map<String, Object> leg2=new HashMap<String, Object>();
        leg2.put("leftOrRight", "right");  // 右腿
        leg2.put("length", 20.3);  // 腿长多少
        leg2.put("hair", 3050);  //有多少腿毛
        // 给他两条腿
        List<Map<String, Object> > legs=new ArrayList<Map<String, Object> >();
        legs.add(leg1);
        legs.add(leg2);
        person.put("leg",legs);

        // 让这个人变成一个Context，以便Jexl认识他
        JexlContext context = new MapContext(person);
        JexlEngine engine = new JexlBuilder().create(); // // 定义引擎， 1.1与2.1的用法不同，1.1使用的是工厂
        // 看看这个人是否年龄在30岁以上，并且身上有超过100元现金
        boolean yes=(Boolean)engine.createExpression( "age>30 && cash>100" ).evaluate(context);
        System.out.println("年龄在30岁以上，并且身上有超过100元现金?  "+yes);  // 他没有

        // 看看这个人是否左腿上有超过2500根汗毛
        yes=(Boolean)engine.createExpression( "leg[0].hair>2500" ).evaluate(context);

        System.out.println("左腿上有超过2500根汗毛?  "+yes);   // 是的，他有
    }
}

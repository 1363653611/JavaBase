package com.zbcn.QLExpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
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

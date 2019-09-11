package com.zbcn.common.base.annotion.db;


import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DBTable {

    String name() default "";
}

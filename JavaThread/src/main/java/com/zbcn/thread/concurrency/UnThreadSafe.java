package com.zbcn.thread.concurrency;

import java.lang.annotation.*;

/**
 * 非线程安全
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface UnThreadSafe {

    String value() default "非线程安全";
}

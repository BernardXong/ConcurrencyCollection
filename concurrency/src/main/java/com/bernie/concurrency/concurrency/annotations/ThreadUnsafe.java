package com.bernie.concurrency.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Bernie Xiong【xiongyun100@163.com】
 * @version: v1.0
 * @description: 用于标记【线程不安全】的类或者写法
 * @Date 2020/2/20 15:53
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadUnsafe {
    String value() default "";
}

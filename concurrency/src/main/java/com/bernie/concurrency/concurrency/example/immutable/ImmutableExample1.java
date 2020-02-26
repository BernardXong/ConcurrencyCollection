package com.bernie.concurrency.concurrency.example.immutable;

import com.bernie.concurrency.concurrency.annotations.ThreadUnsafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * ImmutableExample1
 *
 * @Description final修饰基本类型变量，值不可以修改；修饰引用类型变量，引用初始化后不可以指向其他对象
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/2/24
 */
@Slf4j
@ThreadUnsafe
public class ImmutableExample1 {
    private final static Integer a = 1;

    private final static String b = "2";

    private final static Map<Integer,Integer> map = Maps.newConcurrentMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    private static void test(final int a){
//        a = 3;
    }

    public static void main(String[] args) {
//        a = 2;
//        b = "3";
//        map = Maps.newConcurrentMap();
        map.put(1,3);
        System.out.println(map.get(1));
    }
}

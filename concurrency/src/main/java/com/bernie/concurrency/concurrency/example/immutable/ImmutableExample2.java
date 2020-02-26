package com.bernie.concurrency.concurrency.example.immutable;

import com.bernie.concurrency.concurrency.annotations.ThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * ImmutableExample2
 *
 * @Description 利用Collections的unmodifiableXXX方法可以对Map Set对象做不可修改控制的集合
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/2/24
 */
@Slf4j
@ThreadSafe
public class ImmutableExample2 {


    private static Map<Integer,Integer> map = Maps.newConcurrentMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        map = Collections.unmodifiableMap(map);

    }


    public static void main(String[] args) {
        map.put(1,3);
        System.out.println(map.get(1));
    }
}

package com.bernie.concurrency.example.immutable;

import com.bernie.concurrency.annotations.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;

/**
 * ImmutableExample3
 *
 * @Description 使用guava的ImmutableXXX对象可以做不可修改对象
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/2/24
 */
@Slf4j
@ThreadSafe
public class ImmutableExample3 {

    private static final ImmutableList<Integer> list = ImmutableList.of(1,2,3);

    private static final ImmutableSet set = ImmutableSet.of(list);

    private static final ImmutableMap<Integer,Integer> map = ImmutableMap.of(1,2,3,4);

    private static final ImmutableMap<Integer,Integer> map2 = ImmutableMap.<Integer,Integer>builder()
            .put(1,2)
            .put(3,4)
            .put(5,6).build();


    public static void main(String[] args) {
        list.add(4);
        set.add(4);
        map.put(1,4);
        map2.put(1,4);

        log.info("map key=1 value:{}",map2.get(1));
    }
}

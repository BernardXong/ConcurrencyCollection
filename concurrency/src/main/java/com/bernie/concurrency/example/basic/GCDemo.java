package com.bernie.concurrency.example.basic;

/**
 * GCDemo
 *
 * @Description GC测试代码
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/3/8
 */
public class GCDemo {
    public static void main(String[] args) {
        //对象型变量，在加1操作，会不断new新对象出来，在新生代Eden区就会很大
        Long sum = 0L;
        for(long i=0;i<Integer.MAX_VALUE;i++){
            sum = sum + i;
        }
    }
}

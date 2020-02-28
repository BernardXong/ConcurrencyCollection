package com.bernie.concurrency.example.sync;

import com.bernie.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SynchronizedExample
 *
 * @Description TODO
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/2/21
 */
@Slf4j
@ThreadSafe
public class SynchronizedExample2 {

    //修饰一个类
    public static void test1(int j){
        synchronized (SynchronizedExample2.class){
            for(int i=0;i<10;i++){
                log.info("test1 {} - {}",j,i);
            }
        }
    }

    //修饰一个静态方法
    public static synchronized void test2(int j){
        for(int i=0;i<10;i++){
            log.info("tes2 {} - {}",j,i);
        }
    }

    public static void main(String[] args){
        SynchronizedExample2 synchronizedExample1 = new SynchronizedExample2();
        SynchronizedExample2 synchronizedExample2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(()->{
//            synchronizedExample1.test2(1);
//        });
//        executorService.execute(()->{
//            synchronizedExample2.test2(2);
//        });

        executorService.execute(()->{
            synchronizedExample1.test1(1);
        });
        executorService.execute(()->{
            synchronizedExample2.test1(2);
        });
    }
}

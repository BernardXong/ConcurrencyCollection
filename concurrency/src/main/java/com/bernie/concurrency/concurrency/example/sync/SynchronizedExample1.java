package com.bernie.concurrency.concurrency.example.sync;

import com.bernie.concurrency.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
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
public class SynchronizedExample1 {

    //修饰一个代码块
    public void test1(){
        synchronized (this){
            for(int i=0;i<10;i++){
                log.info("test1 - {}",i);
            }
        }
    }

    //修饰一个方法
    public synchronized void test2(){
        for(int i=0;i<10;i++){
            log.info("tes2 - {}",i);
        }
    }

    public static void main(String[] args){
        SynchronizedExample1 synchronizedExample1 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(()->{
//            synchronizedExample1.test1();
//        });
//        executorService.execute(()->{
//            synchronizedExample1.test1();
//        });

        executorService.execute(()->{
            synchronizedExample1.test2();
        });
        executorService.execute(()->{
            synchronizedExample1.test2();
        });
    }
}

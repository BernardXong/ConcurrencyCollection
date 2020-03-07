package com.bernie.concurrency.example.aqs;

import com.bernie.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * SemaphoreExample1
 *
 * @Description 信号量实例
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/3/7
 */
@Slf4j
@ThreadSafe
public class SemaphoreExample1 {

    //同时并发的执行线程数
    public static int threadTotal = 20;
    public static int concurrencyNum = 3;
    public static void main(String[] args)throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(concurrencyNum);

        for(int i=0;i<threadTotal;i++){
            final int threadNo = i;
            executorService.execute(()->{
                try{
                    //信号量判断当前并发的线程是否有3个，有的话，不返回结果，也不执行后面的test方法
                    semaphore.acquire();
                    test(threadNo);
                    //在当前并发范围内执行了结果，信号量放行一个线程
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception:{}",e);
                }
            });
        }
        executorService.shutdown();
        log.info("finish");
    }

    private static void test(int threadNo)throws Exception{
        log.info("线程号:{}",threadNo);
        Thread.sleep(1000);
    }
}

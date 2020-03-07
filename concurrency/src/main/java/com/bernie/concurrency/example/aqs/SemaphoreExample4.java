package com.bernie.concurrency.example.aqs;

import com.bernie.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * SemaphoreExample1
 *
 * @Description 信号量实例，尝试获取信号量
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/3/7
 */
@Slf4j
@ThreadSafe
public class SemaphoreExample4 {

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
                    //尝试获取一个许可，获取后就执行,同时超时时间5s，表示5s内火尝试获取信号量，有空余就获取
                    if(semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)){
                        test(threadNo);
                        //在当前并发范围内执行了结果，信号量放行许可，一个线程释放一个
                        semaphore.release();
                    }
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

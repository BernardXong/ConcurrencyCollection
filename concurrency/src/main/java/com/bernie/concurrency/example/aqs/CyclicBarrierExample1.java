package com.bernie.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrierExample1
 *
 * @Description 栅栏模式例子
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/3/7
 */
@Slf4j
public class CyclicBarrierExample1 {
    //定义多少个线程需要等待
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    public static void main(String[] args) throws Exception{
        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i=0;i<10;i++){
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(()->{
                try {
                    race(threadNum);
                }catch (Exception e){
                    log.error(e.getMessage());
                }
            });

        }
        executor.shutdown();
    }

    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{} is await!",threadNum);
        cyclicBarrier.await();
        log.info("{} is continue",threadNum);
    }

}

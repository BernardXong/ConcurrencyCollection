package com.bernie.concurrency.example.aqs;

import com.bernie.concurrency.annotations.Recommend;
import com.bernie.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Bernie
 * @CreateTime: 2020-03-12 16:29
 * @Description: CountDownLatch 用法
 * @Email: xiongyun100@163.com
 */
@Slf4j
@ThreadSafe
@Recommend
public class CountDownLatchExample1 {

    private static int threadCount = 200;

    public static void main(String[] args)throws InterruptedException{
        ExecutorService service = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i=0;i<threadCount;i++){
            final int threadNo = i;
            service.execute(()->{
                try{
                    test(threadNo);
                }catch (Exception e){
                    log.error("exception:{}",e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        log.info("countDown结束");
        service.shutdown();
    }

    private static void test(int threadNo)throws Exception{
        Thread.sleep(100);
        log.info("{}",threadNo);
        Thread.sleep(100);
    }
}

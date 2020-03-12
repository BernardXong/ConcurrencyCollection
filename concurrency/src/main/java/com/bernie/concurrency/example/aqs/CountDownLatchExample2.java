package com.bernie.concurrency.example.aqs;

import com.bernie.concurrency.annotations.Recommend;
import com.bernie.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Bernie
 * @CreateTime: 2020-03-12 16:29
 * @Description: CountDownLatch 用法2:再指定时间内减数，超时就不管继续执行后续逻辑
 * 注意：线程池在关闭得时候，会等待正在执行得线程全部完成再关闭
 * @Email: xiongyun100@163.com
 */
@Slf4j
@ThreadSafe
@Recommend
public class CountDownLatchExample2 {

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
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("countDown结束");
        service.shutdown();
    }

    private static void test(int threadNo)throws Exception{
        Thread.sleep(100);
        log.info("{}",threadNo);
        Thread.sleep(100);
    }
}

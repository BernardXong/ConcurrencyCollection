package com.bernie.concurrency.example.concurrent;

import com.bernie.concurrency.annotations.ThreadSafe;
import com.bernie.concurrency.annotations.ThreadUnsafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * CopyOnWriteArrayList
 *
 * @Description CopyOnWriteArrayList是线程安全的，在写操作会加锁。
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/2/28
 */
@Slf4j
@ThreadSafe
public class CopyOnWriteArrayListExample {
    //请求总数
    public static int clientTotal = 5000;
    //同时并发的执行线程数
    public static int threadTotal = 200;

    private static List<Integer> list = new CopyOnWriteArrayList<>();
    public static void main(String[] args)throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for(int i=0;i<clientTotal;i++){
            final int count = i;
            executorService.execute(()->{
                try{
                    //信号量判断当前并发的线程是否有200个，有的话，不返回结果，也不执行后面的add方法
                    semaphore.acquire();
                    update(count);
                    //在当前并发范围内执行了结果，信号量放行一个线程
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception:{}",e);
                }
                //5000个线程请求完成一个的时候，就减一
                countDownLatch.countDown();
            });
        }
        //当所有请求5000个都执行完成后。执行await后面的逻辑
        countDownLatch.await();
        executorService.shutdown();
        log.info("list size is:{}",list.size());
    }

    private static void update(int i){
        list.add(i);
    }
}

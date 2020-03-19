package com.bernie.concurrency.example.pool;

import com.bernie.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Bernie
 * @CreateTime: 2020-03-18 16:58
 * @Description: 线程池介绍 newSingleThreadExecutor 只使用一个线程处理
 * @Email: xiongyun100@163.com
 */
@Slf4j
@ThreadSafe
public class ThreadPoolExample3 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for(int i=0;i<10;i++){
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("task {}",index);
                }
            });
        }
        executorService.shutdown();
    }


}

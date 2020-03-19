package com.bernie.concurrency.example.pool;

import com.bernie.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Bernie
 * @CreateTime: 2020-03-18 16:58
 * @Description: 线程池介绍 newScheduledThreadPool 使用定时调度的线程池
 * @Email: xiongyun100@163.com
 */
@Slf4j
@ThreadSafe
public class ThreadPoolExample4 {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

//        executorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                log.info("schedule task run");
//            }
//        },3, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("scheduleAtFixedRate task run");
            }
        },1,3,TimeUnit.SECONDS);
        //executorService.shutdown();

//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                log.info("TimerTask task run");
//            }
//        },new Date(),5*1000);
    }


}

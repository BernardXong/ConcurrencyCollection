package com.bernie.concurrency.example.Lock;

import com.bernie.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Bernie
 * @CreateTime: 2020-03-13 14:55
 * @Description: ReentrantLock锁对Condition使用示例(慕课版)
 * 线程1获取锁后，被条件释放，线程1释放锁进入AQS条件队列；
 * 线程2启动后进入AQS等待队列，当线程1释放锁后，线程2获取锁
 * 线程2执行唤醒锁，此时线程1被唤醒，从条件队列进入等待队列(此时线程2没有释放锁)
 * 线程2释放锁后，结束线程，出等待队列，线程1在等待队列获取锁继续执行
 * @Email: xiongyun100@163.com
 */
@Slf4j
@ThreadSafe
public class ReentrantLockConditionExample {

    public static void main(String[] args)throws Exception {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(()->{
            try{
                lock.lock();
                log.info("wait signal");//1 等待信号
                condition.await();//当前锁释放，进入AQS等待队列
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            log.info("get signal");// 4 获取信号
            lock.unlock();
        }).start();

        new Thread(()->{
            lock.lock();//线程2获取锁
            log.info("get lock");// 2 得到锁
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            condition.signalAll();//线程2 唤醒锁
            log.info("send signal");// 3 发送信号
            lock.unlock();
        }).start();
    }

}

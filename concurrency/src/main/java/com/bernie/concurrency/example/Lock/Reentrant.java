package com.bernie.concurrency.example.Lock;

import com.bernie.concurrency.annotations.Recommend;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Bernie
 * @CreateTime: 2020-03-12 14:45
 * @Description: J.U.C Reentrant 可重入锁
 * @Email: xiongyun100@163.com
 */
@Slf4j
@Recommend
public class Reentrant {
    ReentrantLock lock = new ReentrantLock();

    public void a()throws InterruptedException{
        log.info("当前锁数量:{}",lock.getHoldCount());
        lock.lock();
        doSamething();
        lock.unlock();
    }

    public void doSamething()throws InterruptedException{
        log.info("第二次尝试获取锁");
        lock.lock();
        log.info("第二次锁后锁数量:{}",lock.getHoldCount());
        //TODO
        lock.unlock();
        log.info("解锁一次后锁后锁数量:{}",lock.getHoldCount());
    }
    public static void main(String[] args) throws InterruptedException{
        Reentrant reentrant = new Reentrant();
        reentrant.a();
        reentrant.doSamething();
    }

}

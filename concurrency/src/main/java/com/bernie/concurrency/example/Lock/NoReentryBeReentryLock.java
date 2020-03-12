package com.bernie.concurrency.example.Lock;

import com.bernie.concurrency.annotations.Recommend;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Bernie
 * @CreateTime: 2020-03-12 14:45
 * @Description: 不可重入锁改造为可重入锁   计数器+判断当前线程
 * @Email: xiongyun100@163.com
 */
@Slf4j
@Recommend
public class NoReentryBeReentryLock {
    Lock lock = new Lock();

    public void a()throws InterruptedException{
        log.info("当前锁数量:{}",lock.getHoldCount());
        lock.lock();
        doSamething();
        lock.unLock();
    }

    public void doSamething()throws InterruptedException{
        log.info("第二次尝试获取锁");
        lock.lock();
        log.info("第二次锁后锁数量:{}",lock.getHoldCount());
        //TODO
        lock.unLock();
        log.info("解锁一次后锁后锁数量:{}",lock.getHoldCount());
    }
    public static void main(String[] args) throws InterruptedException{
        NoReentryBeReentryLock noReentryLock = new NoReentryBeReentryLock();
        noReentryLock.a();
        noReentryLock.doSamething();
    }

    class Lock{
        //是否占用
        private boolean isLock = false;
        private Thread lockedBy = null;
        private int holdCount = 0;
        //使用锁
        public synchronized void lock() throws InterruptedException{
                Thread t = Thread.currentThread();
                while(isLock && lockedBy != t){
                       log.info("进入：可重入状态！！");
                        wait();
                }
                log.info("获取锁");
                isLock = true;
                lockedBy = t;
                holdCount ++;
        }

        //释放锁
        public synchronized void unLock(){

            if(Thread.currentThread() == lockedBy){
                holdCount --;
                if(0==holdCount){
                    isLock = false;
                    lockedBy = null;
                    notify();
                }

            }

        }

        public int getHoldCount() {
            return holdCount;
        }
    }
}

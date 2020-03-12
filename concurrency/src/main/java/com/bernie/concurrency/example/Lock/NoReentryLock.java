package com.bernie.concurrency.example.Lock;

import com.bernie.concurrency.annotations.Recommend;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Bernie
 * @CreateTime: 2020-03-12 14:45
 * @Description: 不可重入锁示例
 * @Email: xiongyun100@163.com
 */
@Slf4j
@Recommend
public class NoReentryLock {
    Lock lock = new Lock();

    public void a()throws InterruptedException{
        lock.lock();
        doSamething();
        lock.unLock();
    }

    public void doSamething()throws InterruptedException{
        log.info("第二次尝试获取锁");
        lock.lock();
        //TODO
        lock.unLock();
    }
    public static void main(String[] args) throws InterruptedException{
        NoReentryLock noReentryLock = new NoReentryLock();
        noReentryLock.a();
        noReentryLock.doSamething();
    }

    class Lock{
        //是否占用
        private boolean isLock = false;
        //使用锁
        public synchronized void lock() throws InterruptedException{

                while(isLock){
                       log.info("进入：不可重入状态！！");
                        wait();
                }
                log.info("第一次获取锁");
                isLock = true;
        }

        //释放锁
        public void unLock(){
            isLock = false;
            notify();
        }
    }
}

package com.bernie.concurrency.example.Lock;

import com.bernie.concurrency.annotations.Recommend;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Bernie
 * @CreateTime: 2020-03-12 14:45
 * @Description: 可重入锁示例
 * @Email: xiongyun100@163.com
 */
@Slf4j
@Recommend
public class ReentryLock {
    public void demoTest(){
        //第一次获取锁
        synchronized (this){
            while(true){
                //再一次获取锁
                synchronized (this){
                    log.info("可重入!");
                }
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }
    public static void main(String[] args) {
        ReentryLock reentryLock = new ReentryLock();
        reentryLock.demoTest();
    }
}

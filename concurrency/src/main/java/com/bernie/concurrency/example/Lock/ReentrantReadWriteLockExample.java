package com.bernie.concurrency.example.Lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: Bernie
 * @CreateTime: 2020-03-13 15:37
 * @Description: 可重入读写锁(慕课版)，特点在没有任何读写锁的时候，才能执行写锁(悲观读取)
 * 容易造成写锁饥饿
 * @Email: xiongyun100@163.com
 */
@Slf4j
public class ReentrantReadWriteLockExample {
    private final Map<String,Data> map = new TreeMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public Data get(String key){
        readLock.lock();
        try{
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys(){
        readLock.lock();
        try{
            return map.keySet();
        }finally {
            readLock.unlock();
        }
    }

    public Data put(String key,Data value){
        writeLock.lock();
        try{
            return map.put(key,value);
        }finally {
            writeLock.unlock();
        }

    }
    class Data{

    }
}

package com.bernie.concurrency.concurrency.example.singleton;

import com.bernie.concurrency.concurrency.annotations.Recommend;
import com.bernie.concurrency.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式SingletonExample5
 * 单例实例在第一次被使用的时候创建
 *
 * @Description volatile+双重检测同步锁单例模式，是线程安全的模式，volatile关键字加双重检测机制可以防止CPU的指令重排
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/2/22
 */
@ThreadSafe
@Recommend
public class SingletonExample5 {


    //私有构造函数
    private SingletonExample5(){
        //实例化的时候，cpu内部是如下执行的指令
        //1.memory = allocate() 分配对象的内存空间
        //2.ctorInstance() 初始化对象
        //3.instance = memory 设置instance指向刚分配的内存
    }
    //单例对象 volatile+双重检测机制 ->禁止指令重排
    private static volatile SingletonExample5 instance = null;

    //静态的工厂方法
    public static SingletonExample5 getInstance(){//双重检测机制
        if(instance == null){
            synchronized (SingletonExample5.class){//同步锁
                if(instance == null){
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}

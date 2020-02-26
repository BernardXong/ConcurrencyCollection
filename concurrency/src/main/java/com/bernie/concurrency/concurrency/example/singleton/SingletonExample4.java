package com.bernie.concurrency.concurrency.example.singleton;

import com.bernie.concurrency.concurrency.annotations.ThreadUnsafe;
import com.bernie.concurrency.concurrency.annotations.UnRecommend;

/**
 * 懒汉模式SingletonExample4 双重同步锁单例模式
 * 单例实例在第一次被使用的时候创建
 *
 * @Description 双重同步锁单例模式是线程不安全的单例模式写法，因为在多线程的情况下，类实例化会产生CPU指令重排，一旦重排，则会出错
 * 只能重排是由JMM和CPU同步优化产生的
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/2/22
 */
@ThreadUnsafe
@UnRecommend
public class SingletonExample4 {

    //私有构造函数
    private SingletonExample4(){
        //实例化的时候，cpu内部是如下执行的指令
        //1.memory = allocate() 分配对象的内存空间
        //2.ctorInstance() 初始化对象
        //3.instance = memory 设置instance指向刚分配的内存

        //JMM和CPU优化时
        //1.memory = allocate() 分配对象的内存空间
        //3.instance = memory 设置instance指向刚分配的内存
        //2.ctorInstance() 初始化对象
    }
    //单例对象
    private static SingletonExample4 instance = null;

    //静态的工厂方法
    public static SingletonExample4 getInstance(){//双重检测机制
        if(instance == null){
            synchronized (SingletonExample4.class){//同步锁
                if(instance == null){
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}

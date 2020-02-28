package com.bernie.concurrency.example.singleton;

import com.bernie.concurrency.annotations.ThreadUnsafe;
import com.bernie.concurrency.annotations.UnRecommend;

/**
 * 懒汉模式SingletonExample3
 * 单例实例在第一次被使用的时候创建
 *
 * @Description 改造后的懒汉模式是线程安全的单例模式写法，用synchronized修饰后同时只能一个线程访问;
 * 不推荐，性能的开销较大
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/2/22
 */
@ThreadUnsafe
@UnRecommend
public class SingletonExample3 {

    //私有构造函数
    private SingletonExample3(){

    }
    //单例对象
    private static SingletonExample3 instance = null;

    //静态的工厂方法
    public static synchronized SingletonExample3 getInstance(){
        if(instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}

package com.bernie.concurrency.concurrency.example.singleton;

import com.bernie.concurrency.concurrency.annotations.ThreadUnsafe;
import com.bernie.concurrency.concurrency.annotations.UnRecommend;

/**
 * 懒汉模式SingletonExample1
 * 单例实例在第一次被使用的时候创建
 *
 * @Description 线程不安全的单例模式写法：主要在判断实例为空的地方，如果两个线程都在判断实例是否为空，就会构造函数实例化两次。
 * 每次实例化对象底层都会做运算，这样会有出错概率。
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/2/22
 */
@ThreadUnsafe
@UnRecommend
public class SingletonExample1 {

    //私有构造函数
    private SingletonExample1(){

    }
    //单例对象
    private static SingletonExample1 instance = null;

    //静态的工厂方法
    public static SingletonExample1 getInstance(){
        if(instance == null){
            instance = new SingletonExample1();
        }
        return instance;
    }
}

package com.bernie.concurrency.example.singleton;

import com.bernie.concurrency.annotations.Recommend;
import com.bernie.concurrency.annotations.ThreadSafe;

/**
 * 恶汉模式SingletonExample2
 * 单例实例在类装载时创建
 *
 * @Description 线程安全的单例模式写法：但如果构造函数处理逻辑太多，会导致性能问题；恶汉模式会如果类初始化不调用，会造成资源的浪费。
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/2/22
 */
@ThreadSafe
@Recommend
public class SingletonExample2 {

    //私有构造函数
    private SingletonExample2(){

    }
    //单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    //静态的工厂方法
    public static SingletonExample2 getInstance(){
        return instance;
    }
}

package com.bernie.concurrency.example.singleton;

import com.bernie.concurrency.annotations.Recommend;
import com.bernie.concurrency.annotations.ThreadSafe;

/**
 * SingletonExample7
 *
 * @Description 枚举模式的单例，是最安全的方式。
 * 相对懒汉模式在安全性容易保证；
 * 相对饿汉模式在实际屌用才做初始化，在后续使用可以直接取到值，不会资源浪费。
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/2/22
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    //私有构造函数
    private SingletonExample7(){

    }

    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton {
        INSTANCE;
        private SingletonExample7 singleton;
        //JVM保证这个方法绝对只调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getSingleton(){
            return singleton;
        }
    }
}

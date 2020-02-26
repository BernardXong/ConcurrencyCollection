package com.bernie.concurrency.concurrency.example.threadLocal;

/**
 * RequestHolder
 *
 * @Description TODO
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/2/26
 */
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    //请求刚刚接受到的时候到的时候
    public static void add(Long id){
        requestHolder.set(id);
    }

    //请求处理中
    public static Long get(){
        return requestHolder.get();
    }

    //请求执行完后处理
    public static void remove(){
        requestHolder.remove();
    }
}

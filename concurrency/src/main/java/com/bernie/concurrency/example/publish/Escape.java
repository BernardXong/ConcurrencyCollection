package com.bernie.concurrency.example.publish;

import com.bernie.concurrency.annotations.ThreadUnsafe;
import com.bernie.concurrency.annotations.UnRecommend;
import lombok.extern.slf4j.Slf4j;

/**
 * Escape
 *
 * @Description 对象再未完成构造之前不可以将其发布-示例
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/2/22
 */
@Slf4j
@ThreadUnsafe
@UnRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape(){
        //实例化内部类相当于新启一个线程
        new InnerClass();
    }

    private class InnerClass{

        public InnerClass(){
            //this引用在构造期间被发布的错误
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args){
            new Escape();
    }
}

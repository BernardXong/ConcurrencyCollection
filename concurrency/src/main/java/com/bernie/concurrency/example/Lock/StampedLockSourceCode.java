package com.bernie.concurrency.example.Lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @Author: Bernie
 * @CreateTime: 2020-03-13 15:37
 * @Description: 票据锁源码(慕课版)，特点支持读锁，写锁，乐观锁
 * @Email: xiongyun100@163.com
 */
@Slf4j
public class StampedLockSourceCode {
   class Point{
       private double x,y;
       private final StampedLock s1 = new StampedLock();

       void move(double deltaX,double deltaY){
            long stampe = s1.writeLock();
            try{
                x+=deltaX;
                y+=deltaY;
            }finally {
                s1.unlockWrite(stampe);
            }
       }

       //下面看看乐观锁读案例
       double distanceFromOrigin(){//A read-only method
            long stampe = s1.tryOptimisticRead();//获得一个乐观锁
            double currentX = x,currentY = y; //将两个字段读入本地局部变量
            if(!s1.validate(stampe)){//检查发出乐观读锁后同时是否有其他锁发生？
                stampe = s1.readLock();//如果没有，我们再次获取读悲观锁
                try{
                   currentX = x;//将两个字段读入本地局部变量
                   currentY = y;//将两个字段读入本地局部变量
                }finally {
                    s1.unlockRead(stampe);
                }
            }
            return Math.sqrt(currentX*currentX +currentY*currentY);
       }

       //下面看看悲观锁读案例
       void moveIfAtOrigin(double newX,double newY){
            //Could instead start with optimistic,not read mode
            long stampe = s1.readLock();
            try{
                while(x == 0.0 && y == 0.0){//循环，检查当前状态是否符合
                    long ws = s1.tryConvertToWriteLock(stampe);//将读锁改为写锁
                    if(ws != 0L){//确认转为写锁是否成功
                        stampe = ws;//如果成功，替换票据
                        x = newX;//进行状态转变
                        y = newY;//进行状态转变
                        break;
                    }else{//如果不成功转为写锁
                        s1.unlockRead(stampe);//显示释放读锁
                        stampe = s1.writeLock();//显示直接进行写锁，然后再通过循环再试
                    }
                }
            }finally {
                s1.unlock(stampe);//释放读锁或写锁
            }
       }
   }
}

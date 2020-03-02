package com.bernie.concurrency.example.syncContainer;

import com.bernie.concurrency.annotations.ThreadUnsafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;

/**
 * @Author: Bernie
 * @CreateTime: 2020-03-02 9:53
 * @Description: 同步容器:快速失败例子,报java.util.ConcurrentModificationException异常，
 * 每次迭代器会调用modCount，与ecpectCount做对比，如果modCount修改，会报错
 * @Email: xiongyun100@163.com
 */
@Slf4j
@ThreadUnsafe
public class FastFailExample {

    //java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> vector1){
        for(Integer v : vector1){
            if(v.equals(3)){
                vector1.remove(v);
            }
        }
    }

    //java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> vector2){
        Iterator<Integer> iteator = vector2.iterator();
        while(iteator.hasNext()){
            Integer i = iteator.next();
            if(i.equals(3)){
                vector2.remove(i);
            }
        }

    }

    private static void test3(Vector<Integer> vector3){
        for(int i=0;i<vector3.size();i++){
            if(vector3.get(i).equals(3)){
                vector3.remove(i);
            }
        }
    }

    //success
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
//        test1(vector);
//        test2(vector);
        test3(vector);
    }
}

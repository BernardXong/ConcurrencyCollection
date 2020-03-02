package com.bernie.concurrency.example.syncContainer;

import com.bernie.concurrency.annotations.ThreadUnsafe;
import lombok.extern.slf4j.Slf4j;
import java.util.Vector;


/**
 * @Author: Bernie
 * @CreateTime: 2020-03-02 9:53
 * @Description: 同步容器：Vector
 * @Email: xiongyun100@163.com
 */
@Slf4j
@ThreadUnsafe
public class VectorExample2 {
    private static Vector<Integer> vector = new Vector();


    public static void main(String[] args) {
        while(true){
            for(int i=0;i<10;i++){
                vector.add(i);
            }

            Thread thread1 = new Thread(){
                @Override
                public void run() {
                    for(int i=0;i<vector.size();i++){
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread(){
                @Override
                public void run() {
                    for(int i=0;i<vector.size();i++){
                        vector.get(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }
}

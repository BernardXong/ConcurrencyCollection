package com.bernie.concurrency.example.basic;

/**
 * DoEscapeAnalysis
 *
 * @Description jdk6后逃逸分析的作用 编译配置 VM Option "-Xmx10m -Xms10m -XX:+PrintGC -XX:-DoEscapeAnalysis"
 * jdk6以后默认是打开逃逸分析的，如果关闭逃逸分析就加上上面"-XX:-DoEscapeAnalysis"这段
 * @Author Bernie【xiongyun100@163.com】
 * @Date 2020/3/7
 */
public class DoEscapeAnalysis {

    public static void main(String[] args) {
        while (true) {
            //申明对象只有在当前函数调用
            Integer i = new Integer(11111);
        }
    }
}

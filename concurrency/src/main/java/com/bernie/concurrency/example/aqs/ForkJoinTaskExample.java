package com.bernie.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @Author: Bernie
 * @CreateTime: 2020-03-14 16:45
 * @Description: AQS之Fork/Join，Fork是将一个任务拆分为很多小任务，小任务之间再队列连接，行成多个队列让多个线程并行执行(一个线程一条队列)；
 * 同时让多个线程的结果合并(期间支持队列窃取，A线程执行完队列内容，B线程还没执行完，A线程从B线程队列的尾部窃取执行)。
 * @Email: xiongyun100@163.com
 */
@Slf4j
public class ForkJoinTaskExample extends RecursiveTask<Integer> {

    public static final int threshold = 2;
    private int start;
    private int end;

    public ForkJoinTaskExample(int start,int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        //如果足够小就计算任务
        boolean canCompute = (end - start) <= threshold;
        if(canCompute){
            for(int i=start;i<=end;i++){
                sum += i;
            }

        }else{
            //如果任务大于阈值，就分裂为两个子任务计算
            int middle = (start + end)/2;

            ForkJoinTaskExample leftTask = new ForkJoinTaskExample(start,middle);
            ForkJoinTaskExample rightTask = new ForkJoinTaskExample(middle+1,end);

            //执行子任务
            leftTask.fork();
            rightTask.fork();

            //等待执行结果并合并结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            //合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        //生成一个计算任务，1+2+3...+100求和
        ForkJoinTaskExample task = new ForkJoinTaskExample(1,100);

        //执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);

        try{
            log.info("result：{}",result.get());
        }catch (Exception e){
            log.error("exception",e);
        }
    }
}

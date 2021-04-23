package com.aki.thread3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 170725e on 2018/8/27.
 */
public class TestQueue4 {
    public static void main(String[] args) {

        //4.newSingleThreadExecutor
        //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。示例代码如下：
        //结果依次输出，相当于顺序执行各个任务。
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            singleThreadExecutor.execute(new Thread1(i,singleThreadExecutor));
        }
        System.out.println("结束");
    }
}

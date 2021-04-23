package com.aki.thread3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 170725e on 2018/8/27.
 */
public class TestQueue1 {
    public static void main(String[] args) {
        //1.newCachedThreadPool
        //创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。示例代码如下：
        //线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            cachedThreadPool.execute(new Thread1(i, cachedThreadPool));
        }
        System.out.println("结束");
    }
}

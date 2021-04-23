package com.aki.thread3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 170725e on 2018/8/27.
 */
public class TestQueue2 {
    public static void main(String[] args) throws InterruptedException {
        //2.newFixedThreadPool
        //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。示例代码如下：
        //因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
        //定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()。可参考PreloadDataCache。
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(new Thread1(i, fixedThreadPool));
        }
        System.out.println("结束");
    }
}

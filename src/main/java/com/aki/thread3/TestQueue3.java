package com.aki.thread3;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 170725e on 2018/8/27.
 */
public class TestQueue3 {
    public static void main(String[] args) {

        //3. newScheduledThreadPool
        //创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：
        //ScheduledExecutorService比Timer更安全，功能更强大，后面会有一篇单独进行对比。
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Thread1(2222, scheduledThreadPool), 3, TimeUnit.SECONDS);//表示延迟3秒执行。
        scheduledThreadPool.scheduleAtFixedRate(new Thread1(3333, scheduledThreadPool), 1, 3, TimeUnit.SECONDS);//表示延迟1秒后每3秒执行一次。

    }
}

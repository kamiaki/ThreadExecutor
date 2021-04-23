package com.aki.thread.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.*;

@RestController
@Slf4j
public class Controller_ExcutorConfig {
    @Resource(name = "asyncServiceExecutor")
    private Executor runThread;

    //CountDownLatch是一个同步的辅助类，它可以允许一个或多个线程等待，直到一组在其它线程中的操作执行完成。
    //一个CountDownLatch会通过一个给定的count数来被初始化。其中await()方法会一直阻塞，直到当前的count被减到0，而这个过程是通过调用countDown()方法来实现的。在await()方法不再阻塞以后，所有等待的线程都会被释放，并且任何await()的子调用都会立刻返回。这是一次性的－－count不能被重置。如果你需要一种能重置count的版本，请考虑使用CyclicBarrier。
    //CountDownlatch是一个多功能的同步工具，可以被用于各种目的。一个CountDownLatch通过一个值为1的count被初始化，来作为一个开/关的门或门闩：所有调用了await()的线程都会在门前等待，直到门被一个线程通过调用countDown()打开。一个被初始化为N的CountDownLatch可以被用来“在N个线程都完成了某种操作（或者一些操作已经被完成了N次）之后创建一个线程”。
    //CountDownLatch一个有用的属性就是它不需要线程们在继续执行之前，调用countDown来等待count被减到0。它简单地阻止了任何调用了await()的线程继续，直到所有的线程都能够通过。

    @RequestMapping(value = "test2")
    public String testMethod() {
        long start = System.currentTimeMillis();
        int length = 100;
        CountDownLatch countDownLatch = new CountDownLatch(length);
        for (int i = 0; i < length; i++) {
            final int j = i;
            runThread.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "---" + j);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();  //这个不管是否异常都需要数量减,否则会被堵塞无法结束
                }
            });
        }
        try {
            //保证之前的所有的线程都执行完成，才会走下面的；
            countDownLatch.await();
            //最多等待10秒，任务完成了立即停止等待，到10秒了也停止等待
//            countDownLatch.await(10,TimeUnit.SECONDS);
            // 这样就可以在下面拿到所有线程执行完的集合结果
        } catch (Exception e) {
            log.error("阻塞异常");
        }
        float exc = (float) (System.currentTimeMillis() - start) / 1000;
        log.info("run time: " + exc + " seconds");

        return "完了完了";
    }
}

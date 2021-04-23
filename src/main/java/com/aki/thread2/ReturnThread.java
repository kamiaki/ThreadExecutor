package com.aki.thread2;

import java.util.concurrent.*;

public class ReturnThread {

    /**
     * submit 与 executor 区别
     * 一：
     * submit()方法，可以提供Future < T > 类型的返回值。
     * executor()方法，无返回值。
     * 二：
     * excute方法会抛出异常,无法从外界捕获。
     * sumbit方法会抛出异常。throw new RuntimeException(e); Future.get(); 可以从外界捕获。
     * 三：
     * excute入参Runnable
     * submit入参可以为Callable，也可以为Runnable。
     * <p>
     * submit方法不是阻塞方法,而Future.get方法是一个阻塞方法
     *
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executor = new ScheduledThreadPoolExecutor(1);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    int i = 1 / 0;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("runnable execute 线程");
            }
        });
        System.out.println("runnable execute 线程 结束");


        Future<?> future = executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程启动");
                try {
                    Thread.sleep(2000);
                    int i = 1/0;
                } catch (Exception e) {
                    System.out.println("线程内拿到的异常");
                    e.printStackTrace();
                    //异常抛到线程外
                    throw new RuntimeException(e);
                }
                System.out.println("runnable submit 线程");
            }
        });
        try {
            future.get();
        } catch (Exception e) {
            System.out.println("线程外,get拿到的异常");
            e.printStackTrace();
        }
        System.out.println("runnable submit 结束");


        Future<String> submit = executor.submit(new Callable<String>() {
            @Override
            public String call()throws Exception {
                Thread.sleep(2000);
                System.out.println("callable线程");
                int i = 1/0;
                return "callable线程返回值";
            }
        });
        System.out.println("callable线程 结束");
        try {
            String s = submit.get();
            System.out.println(s);
        } catch (Exception e) {
            System.out.println("线程外拿到了异常");
            e.printStackTrace();
        }
        System.out.println("callable线程返回值 结束");
        executor.shutdown();
    }
}

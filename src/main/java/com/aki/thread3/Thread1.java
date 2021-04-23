package com.aki.thread3;

import java.util.concurrent.ExecutorService;

class Thread1 implements Runnable{
    private int i;
    private ExecutorService singleThreadExecutor;
    public Thread1(int i, ExecutorService singleThreadExecutor) {
        this.i = i;
        this.singleThreadExecutor = singleThreadExecutor;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("循环: " + i);
        if (i == 9){
            System.out.println("线程池关闭");
            singleThreadExecutor.shutdown();
        }
    }
}

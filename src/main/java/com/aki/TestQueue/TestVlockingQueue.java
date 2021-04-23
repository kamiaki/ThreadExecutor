package com.aki.TestQueue;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;

public class TestVlockingQueue {
    public static void main(String[] args) throws IOException {
        BlockingQueue<String> blockingQueue = new LinkedBlockingDeque(10);
        CountDownLatch count = new CountDownLatch(2);
        Thread thread = new Thread(() -> {
            try {
                for (; true; ) {
                    for (int i = 0; i < 10; i++) {
                        String putElement = "thread I - put" + i;
                        blockingQueue.put(putElement);
                        System.out.println(putElement);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                count.countDown();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                for (; true; ) {
                    String take = blockingQueue.take();
                    System.out.println("thread II - taketaketaketake" + take);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                count.countDown();
            }
        });

        thread.start();
        thread2.start();
        try {
            count.await();
        }catch (Exception e) {
        }

        System.out.println(blockingQueue.toString());
    }
}

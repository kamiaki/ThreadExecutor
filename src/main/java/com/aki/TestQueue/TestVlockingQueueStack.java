package com.aki.TestQueue;

import java.io.IOException;
import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;

public class TestVlockingQueueStack {
    public static void main(String[] args) throws IOException {
        Stack<String> stringStack = new Stack<>();
        CountDownLatch count = new CountDownLatch(2);
        Thread thread = new Thread(() -> {
            try {
                for (; true; ) {
                    for (int i = 0; i < 10; i++) {
                        String putElement = "thread I - put" + i;
                        stringStack.push(putElement);
                        System.out.println(putElement);
                    }
                    Thread.sleep(500);

                    String pop = stringStack.pop();
                    System.out.println("poppoppop - " + pop);
                    Thread.sleep(500);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                count.countDown();
            }
        });
        thread.start();
    }
}

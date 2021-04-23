package com.aki.thread.service;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

public interface Server_AsyncConfig {
    @Async("taskExecutor2")
    Future<String> asyncTask(CountDownLatch latch);

    @Async("taskExecutor2")
    Future<Integer> asyncTask2(CountDownLatch latch);

    @Async("taskExecutor2")
    Future<Integer> asyncTask3(CountDownLatch latch);
}

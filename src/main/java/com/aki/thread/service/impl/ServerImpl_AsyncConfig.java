package com.aki.thread.service.impl;

import com.aki.thread.service.Server_AsyncConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

@Service
@Slf4j
public class ServerImpl_AsyncConfig implements Server_AsyncConfig {
    @Override
    @Async("taskExecutor2")
    public Future<String> asyncTask(CountDownLatch latch) {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
        return new AsyncResult<>("啊啊啊");
    }

    @Override
    @Async("taskExecutor2")
    public Future<Integer> asyncTask2(CountDownLatch latch) {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
        return new AsyncResult<>(20);
    }

    @Override
    @Async("taskExecutor2")
    public Future<Integer> asyncTask3(CountDownLatch latch) {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
        return new AsyncResult<>(30);
    }
}

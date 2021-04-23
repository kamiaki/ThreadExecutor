package com.aki.thread.service.impl;

import com.aki.thread.service.Service_AsyncConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class ServiceImpl_AsyncConfiguration implements Service_AsyncConfiguration {

    @Async("taskExecutor")
    @Override
    public CompletableFuture<String> runThread(String name) {
        try {
            Thread.sleep(3000L);
            System.out.println("运行线程: " + name +" time: "+ System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        return CompletableFuture.completedFuture(name);
        return CompletableFuture.completedFuture(name);
    }
}

package com.aki.thread.controller;

import com.aki.thread.service.Server_AsyncConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RestController
public class Conterller_AsyncConfig {
    @Autowired
    Server_AsyncConfig taskServer;

    @RequestMapping(value = "test3")
    public String test3() {
        CountDownLatch latch = new CountDownLatch(3);
        LocalTime time = LocalTime.now();
        Future<String> integerFuture = taskServer.asyncTask(latch);
        Future<Integer> integerFuture1 = taskServer.asyncTask2(latch);
        Future<Integer> integerFuture2 = taskServer.asyncTask3(latch);
        try {
            //最大等待时间 县城没执行完也往下执行了
            latch.await(5, TimeUnit.SECONDS);
            LocalTime end = LocalTime.now();
            System.out.println(Duration.between(time, end));

            String integer = integerFuture.get();
            Integer integer1 = integerFuture1.get();
            Integer integer2 = integerFuture2.get();
            int i = integer1 + integer2;
            System.out.println(integer + " : " + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "333";
    }
}

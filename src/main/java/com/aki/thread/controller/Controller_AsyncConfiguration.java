package com.aki.thread.controller;


import com.aki.thread.service.Service_AsyncConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class Controller_AsyncConfiguration {
    @Autowired
    Service_AsyncConfiguration serviceAsyncConfiguration;

    @RequestMapping(value = "test")
    public String testMethod() throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();
        CompletableFuture<String> a1 = serviceAsyncConfiguration.runThread("a1");
        CompletableFuture<String> b2 = serviceAsyncConfiguration.runThread("b2");
        CompletableFuture<String> c3 = serviceAsyncConfiguration.runThread("c3");

        // Wait until they are all done
        //join() 的作用：让“主线程”等待“子线程”结束之后才能继续运行
        CompletableFuture.allOf(a1,b2,c3).join();

        float exc = (float)(System.currentTimeMillis() - start)/1000;
        log.info("run time: " + exc + " seconds");
        //返回的就是 CompletableFuture 里面的
        //CompletableFuture 就是获得了一个线程的标识一样
        return c3.get();
    }
}

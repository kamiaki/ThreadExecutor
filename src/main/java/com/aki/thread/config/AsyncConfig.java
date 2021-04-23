package com.aki.thread.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAsync
public class AsyncConfig {
    private static final int THREADS = Runtime.getRuntime().availableProcessors();

    /**
     * @author Mr. Zhang
     * @description 异步线程池
     * @date 2019-04-19 14:21
     * @website https://www.zhangguimin.cn
     */

    @Bean("taskExecutor2")
    public Executor execute() {
        System.out.println(THREADS);
        Executor executor = new ThreadPoolExecutor(THREADS, 2 * THREADS, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1024));
        return executor;
    }

}
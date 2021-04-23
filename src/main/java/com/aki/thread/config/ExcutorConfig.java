package com.aki.thread.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
@Slf4j
public class ExcutorConfig {

    @Bean("asyncServiceExecutor")
    public Executor asyncServiceExecutor() {
        log.info("start executor -->");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数,核心线程会一直存活,即使没有任务需要处理.当线程数小于核心线程数时,
        // 即使现有的线程空闲,线程池也会优先创建新线程来处理任务,而不是直接交给现有的线程处理;
        // 核心线程在allowCoreThreadTimeout被设置为true时会超时退出,默认情况下不会退出..
        executor.setCorePoolSize(5);
        // 当线程数大于或等于核心线程,且任务队列已满时,线程池会创建新的线程,直到线程数量达到maxPoolSize.
        // 如果线程数已等于maxPoolSize,且任务队列已满,则已超出线程池的处理能力,线程池会拒绝处理任务而抛出异常;
        executor.setMaxPoolSize(20);
        // 任务队列容量.从maxPoolSize的描述上可以看出,任务队列的容量会影响到线程的变化,
        // 因此任务队列的长度也需要恰当的设置.我们中给了10000,相当于就是没有上限了....
        executor.setQueueCapacity(100);
        //配置线程池的前缀 调试的时候显示的名字
        executor.setThreadNamePrefix("async-service-");
        //是否允许核心线程空闲退出,默认值为false.一般就使用false的
        executor.setAllowCoreThreadTimeOut(true);
        //策略
//        ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
//        ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
//        ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
//        ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        // 当线程空闲时间达到keepAliveTime,该线程会退出,直到线程数量等于corePoolSize,
        // 如果allowCoreThreadTimeout设置为true,则所有线程均会退出直到线程数量为0.
        executor.setKeepAliveSeconds(60);
        //进行加载
        executor.initialize();
        return executor;
    }
}
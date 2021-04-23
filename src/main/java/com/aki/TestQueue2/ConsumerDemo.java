package com.aki.TestQueue2;

import java.util.concurrent.BlockingQueue;

/**
 * 消费者
 */
public class ConsumerDemo implements Runnable{
    private String consumerName;
    private BlockingQueue queue;//阻塞队列
    //构造函数,传入消费者名称和操作的阻塞队列
    public ConsumerDemo(String consumerName,BlockingQueue queue) {
        this.consumerName = consumerName;
        this.queue = queue;
    }
    @Override
    public void run() {
        while(true){
            try {
                System.out.println(consumerName + ":开始消费任务---:" + queue.take());//消费者从阻塞队列中消费一个随机数
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

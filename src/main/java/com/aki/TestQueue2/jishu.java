package com.aki.TestQueue2;

import java.util.concurrent.BlockingQueue;

public class jishu implements Runnable{
    private String consumerName;
    private BlockingQueue queue;//阻塞队列

    public jishu(String consumerName, BlockingQueue queue) {
        this.consumerName = consumerName;
        this.queue = queue;
    }
    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep(1000);
                System.out.println(consumerName + ":还剩:" + queue.size() + "条");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

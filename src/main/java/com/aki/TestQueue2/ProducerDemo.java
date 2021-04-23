package com.aki.TestQueue2;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * 生产者
 */
public class ProducerDemo implements Runnable {
    private String producerName;
    private BlockingQueue queue;//阻塞队列
    private Random r = new Random();
    //构造函数,传入生产者名称和操作的阻塞队列
    public ProducerDemo(String producerName,BlockingQueue queue) {
        this.producerName = producerName;
        this.queue = queue;
    }
    @Override
    public void run() {
        while(true){
            try {
                int task = r.nextInt(100);  //产生随机数
                System.out.println(producerName + ":开始生产任务:" + task);
                queue.put(task);  //生产者向队列中放入一个随机数
                Thread.sleep(5000);  //减缓生产者生产的速度，如果队列为空，消费者就会阻塞不会进行消费直到有数据被生产出来
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

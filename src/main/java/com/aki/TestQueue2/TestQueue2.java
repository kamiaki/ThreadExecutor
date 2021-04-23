package com.aki.TestQueue2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 2020 5 21 aki
 * 阻塞队列我们常用的有：LinkedBlockingQueue和ArrayBlockingQueue，
 * 它们在各方面还是很大的区别的；ArrayBlockingQueue在put,take操作使用了同一个锁，
 * 两者操作不能同时进行，而LinkedBlockingQueue使用了不同的锁，put操作和take操作可同时进行，
 * 以此来提高整个队列的并发性能。
 *
 * 作为开发者，使用阻塞队列需要注意的一点是：如果构造一个LinkedBlockingQueue对象，
 * 而没有指定其容量大小，LinkedBlockingQueue会默认一个类似无限大小的容量（Integer.MAX_VALUE），
 * 这样的话，如果生产者的速度一旦大于消费者的速度，也许还没有等到队列满阻塞产生，
 * 系统内存就有可能已被消耗殆尽了。
 *
 * put take 阻塞 size 剩几个
 */
public class TestQueue2 {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(2); //定长为2的阻塞队列
        //ExecutorService：真正的线程池接口
        ExecutorService service = Executors.newCachedThreadPool();  //缓存线程池
        //创建4个生产者：
        ProducerDemo p1 = new ProducerDemo("生产者1",queue);
        ProducerDemo p2 = new ProducerDemo("生产者2",queue);
        ProducerDemo p3 = new ProducerDemo("生产者3",queue);
        ProducerDemo p4 = new ProducerDemo("生产者4",queue);
        //创建3个消费者：
        ConsumerDemo c1 = new ConsumerDemo("消费者1----------------------",queue);
        ConsumerDemo c2 = new ConsumerDemo("消费者2----------------------",queue);
        ConsumerDemo c3 = new ConsumerDemo("消费者3----------------------",queue);
        //计数器
        jishu jishu1 = new jishu("计数器",queue);

        //启动线程
        service.execute(jishu1);
        service.execute(p1);
        service.execute(p2);
        service.execute(p3);
        service.execute(p4);
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);
    }
}


package cn.itcast.mq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class SpringRabbitListener {

//    @RabbitListener(queues = "simple.queue")
//    public void listenSimpleQueue(String msg){
//        System.out.println("消费者监听到simple.queue的消息：{ " + msg + " }");
//
//    }
    @RabbitListener(queues = "simple.queue")
//    工作队列
    public void listenWorkQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1监听到simple.queue的消息：{ " + msg + " }  " + LocalTime.now());
        Thread.sleep(20);
    }
    @RabbitListener(queues = "simple.queue")
//    工作队列
    public void listenWorkQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2监听到simple.queue的消息：{ " + msg + " }  " + LocalTime.now() );
        Thread.sleep(200);
    }

}

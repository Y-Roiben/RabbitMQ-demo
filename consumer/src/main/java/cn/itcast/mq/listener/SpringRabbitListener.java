package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
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


    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg) throws InterruptedException {
        System.err.println("消费者1监听到fanout.queue1的消息：{ " + msg + " }  " + LocalTime.now() );
        Thread.sleep(200);
    }
    @RabbitListener(queues = "fanout.queue2")
//    工作队列
    public void listenFanoutQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2监听到fanout.queue2的消息：{ " + msg + " }  " + LocalTime.now() );
        Thread.sleep(200);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void listenDirectQueue1(String msg){
        System.out.println("消费者1监听到direct.queue1的消息：{ " + msg + " }  " + LocalTime.now());
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"}
    ))
    public void listenDirectQueue2(String msg){
        System.out.println("消费者2监听到direct.queue2的消息：{ " + msg + " }  " + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("topic.queue1"),
            exchange = @Exchange(name = "itcast.topic", type = ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void listenTopicQueue1(String msg){
        System.out.println("消费者1监听到topic.queue1的消息：{ " + msg + " }  " + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("topic.queue2"),
            exchange = @Exchange(name = "itcast.topic", type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenTopicQueue2(String msg){
        System.out.println("消费者2监听到topic.queue2的消息：{ " + msg + " }  " + LocalTime.now());
    }

}

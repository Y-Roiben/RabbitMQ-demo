package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMess2SimpleQueue() {
        String queueName = "simple.queue";
        String message = "hello, spring ampq!!!";
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    public void testSendMess2SimpleQueue2() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "hello, message__";
        for (int i = 1; i < 51; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
    }


    @Test
    public void testSendFanoutExchange() {
        String exchange = "itcast.fanout";
        String message = "hello everyone";
        rabbitTemplate.convertAndSend(exchange,"", message);
    }

    @Test
    public void testSendDirectExchange() {
        String exchange = "itcast.direct";
        String message = "hello red";
        rabbitTemplate.convertAndSend(exchange,"red", message);
    }

    @Test
    public void testSendTopicExchange1() {
        String exchange = "itcast.topic";
        String message = "hello, china.weather";
        rabbitTemplate.convertAndSend(exchange,"china.weather", message);
    }

    @Test
    public void testSendObjQueue() {
        Map<String, Object> msg = new HashMap<>();
        msg.put("name", "张三");
        msg.put("age", 18);
        rabbitTemplate.convertAndSend("obj.queue", msg);
    }
}

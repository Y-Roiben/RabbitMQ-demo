package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    @Bean
    public FanoutExchange fanoutExchange(){  // 声明交换机  itcast.fanout
        return new FanoutExchange("itcast.fanout");
    }


    @Bean
    public Queue fanoutQueue1(){  // 声明第一个队列 fanout.queue1
        return new Queue("fanout.queue1");
    }


    @Bean
    public Queue fanoutQueue2(){  // 声明第二个队列 fanout.queue2
        return new Queue("fanout.queue2");
    }


    @Bean
    public Binding fanoutBinding1(Queue fanoutQueue1, FanoutExchange fanoutExchange){
        return BindingBuilder  //    绑定队列1到交换机
                .bind(fanoutQueue1)
                .to(fanoutExchange);
    }


    @Bean
    public Binding fanoutBinding2(Queue fanoutQueue2, FanoutExchange fanoutExchange){
        return BindingBuilder   //    绑定队列2到交换机
                .bind(fanoutQueue2)
                .to(fanoutExchange);
    }


    @Bean
    public Queue objQueue(){
        return new Queue(("obj.queue"));
    }
}

package com.example.mongoreporting.amqp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessagingConfig {

//    @Value("${rabbitmq.queue}")
    public static final String QUEUE = "DocumentRequest";
//    @Value("${rabbitmq.exchange}")
    public static String EXCHANGE = "DocumentRequestExchange";
//    @Value("${rabbitmq.routingKey}")
    public static String ROUTING_KEY = "DocumentRequestRoutingKey";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    public CustomExchange exchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(EXCHANGE,"x-delayed-message",true, false,args);
    }

    @Bean
    public Binding binding(CustomExchange exchange, Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY).noargs();
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}

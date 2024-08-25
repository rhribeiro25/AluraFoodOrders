package br.com.alurafood.orders.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return  rabbitTemplate;
    }

    @Bean
    public RabbitAdmin criaRabbitAdmin(ConnectionFactory conn) {
        return new RabbitAdmin(conn);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    //Exchange, deadLetterExchange and Queues to payments created

    @Bean
    public Queue queuePaymentsCreated() {
        return QueueBuilder
                .nonDurable("alura-food.payments-ms.payments-created.orders-ms")
                .deadLetterExchange("payments.created.orders.dlx")
                .build();
    }

    @Bean
    public Queue dlqQueuePaymentsCreated() {
        return QueueBuilder
                .nonDurable("alura-food.payments-ms.payments-created.orders-ms.dlq")
                .build();
    }

    @Bean
    public FanoutExchange paymentsCreatedFanoutExchange() {
        return ExchangeBuilder
                .fanoutExchange("payments.created.ex")
                .build();
    }

    @Bean
    public FanoutExchange paymentsCreatedDeadLetterExchange() {
        return ExchangeBuilder
                .fanoutExchange("payments.created.orders.dlx")
                .build();
    }

    @Bean
    public Binding bindPaymentsCreatedOrders() {
        return BindingBuilder
                .bind(queuePaymentsCreated())
                .to(paymentsCreatedFanoutExchange());
    }

    @Bean
    public Binding bindDlxPaymentsCreatedOrders() {
        return BindingBuilder
                .bind(dlqQueuePaymentsCreated())
                .to(paymentsCreatedDeadLetterExchange());
    }

    //Exchange, deadLetterExchange and Queues to payments confirmed

    @Bean
    public Queue queuePaymentsConfirmed() {
        return QueueBuilder
                .nonDurable("alura-food.payments-ms.payments-confirmed.orders-ms")
                .deadLetterExchange("payments.confirmed.dlx")
                .build();
    }

    @Bean
    public Queue dlqQueuePaymentsConfirmed() {
        return QueueBuilder
                .nonDurable("alura-food.payments-ms.payments-confirmed.orders-ms.dlq")
                .build();
    }

    @Bean
    public FanoutExchange paymentsConfirmedFanoutExchange() {
        return ExchangeBuilder
                .fanoutExchange("payments.confirmed.ex")
                .build();
    }

    @Bean
    public FanoutExchange paymentsConfirmedDeadLetterExchange() {
        return ExchangeBuilder
                .fanoutExchange("payments.confirmed.dlx")
                .build();
    }

    @Bean
    public Binding bindPaymentsConfirmedOrders() {
        return BindingBuilder
                .bind(queuePaymentsConfirmed())
                .to(paymentsConfirmedFanoutExchange());
    }

    @Bean
    public Binding bindDlxPaymentsConfirmedOrders() {
        return BindingBuilder
                .bind(dlqQueuePaymentsConfirmed())
                .to(paymentsConfirmedDeadLetterExchange());
    }

}

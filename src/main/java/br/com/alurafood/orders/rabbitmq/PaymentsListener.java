package br.com.alurafood.orders.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentsListener {

    @RabbitListener(queues = "alurafood.payments.created")
    public void getPaymentsMessages(Message msg){
        System.out.println(msg.toString());
    }
}

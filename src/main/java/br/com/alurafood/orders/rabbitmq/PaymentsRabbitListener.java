package br.com.alurafood.orders.rabbitmq;

import br.com.alurafood.orders.dto.PaymentDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PaymentsRabbitListener {

    @RabbitListener(queues = "alura-food.payments-ms.payments-details.orders-ms")
    public void getPaymentsMessages(@Payload PaymentDto payment){
        System.out.println(payment.toString());
    }
}

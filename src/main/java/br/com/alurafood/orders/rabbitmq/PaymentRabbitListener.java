package br.com.alurafood.orders.rabbitmq;

import br.com.alurafood.orders.dto.PaymentDto;
import br.com.alurafood.orders.model.OrderStatus;
import br.com.alurafood.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentRabbitListener {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = "alura-food.payments-ms.payments-created.orders-ms")
    public void getPaymentsCreatedMessages(@Payload PaymentDto payment){
        orderService.updatePaymentStatus(payment, OrderStatus.WAITING_PAYMENT);
    }

    @RabbitListener(queues = "alura-food.payments-ms.payments-confirmed.orders-ms")
    public void getPaymentsConfirmedMessages(@Payload PaymentDto payment){
        orderService.updatePaymentStatus(payment, OrderStatus.PAID);
    }
}

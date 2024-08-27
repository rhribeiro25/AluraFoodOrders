package br.com.alurafood.orders.rabbitmq;

import br.com.alurafood.orders.dto.PaymentDto;
import br.com.alurafood.orders.model.Order;
import br.com.alurafood.orders.model.OrderStatus;
import br.com.alurafood.orders.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentsRabbitListener {

    @Autowired
    private OrderRepository orderRepository;

    @RabbitListener(queues = "alura-food.payments-ms.payments-created.orders-ms")
    public void getPaymentsCreatedMessages(@Payload PaymentDto payment){
        Order order = orderRepository.findById(payment.getOrder().getId())
                .orElseThrow(EntityNotFoundException::new);
        order.setOrderStatus(OrderStatus.WAITING_PAYMENT);
        orderRepository.save(order);
    }

    @RabbitListener(queues = "alura-food.payments-ms.payments-confirmed.orders-ms")
    public void getPaymentsConfirmedMessages(@Payload PaymentDto payment){
        Order order = orderRepository.findById(payment.getOrder().getId())
                .orElseThrow(EntityNotFoundException::new);
        order.setOrderStatus(OrderStatus.PAID);
        orderRepository.save(order);
    }
}

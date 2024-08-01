package br.com.alurafood.orders.dto;

import br.com.alurafood.orders.model.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private List<OrderItemDto> orderItems = new ArrayList<>();



}

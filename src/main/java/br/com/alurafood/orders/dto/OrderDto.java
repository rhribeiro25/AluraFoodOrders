package br.com.alurafood.orders.dto;

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
    private String orderStatus;
    private List<OrderItemDto> orderItems = new ArrayList<>();



}

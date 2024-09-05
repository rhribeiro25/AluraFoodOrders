package br.com.alurafood.orders.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;

    @NotNull
    private LocalDateTime orderDate;

    private String orderStatus;
    private List<OrderItemDto> orderItems;



}

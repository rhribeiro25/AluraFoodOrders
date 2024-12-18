package br.com.alurafood.orders.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private Integer qtt;
    private String description;
}

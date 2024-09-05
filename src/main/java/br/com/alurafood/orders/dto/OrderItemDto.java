package br.com.alurafood.orders.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private ProductDto product;

    @NotNull
    @Positive
    private Integer qtt;
    private String description;
}

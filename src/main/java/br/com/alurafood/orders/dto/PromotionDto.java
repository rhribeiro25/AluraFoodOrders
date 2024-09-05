package br.com.alurafood.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto {

    private Long id;

    private Integer discountPercentage;

    private String name;

    private String status;

    private Long productId;

    private LocalDateTime startDate;

    private LocalDateTime finishDate;
}

package br.com.alurafood.orders.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private Long id;
    private BigDecimal payValue;
    private String payName;
    private String payNumber;
    private String payExpiration;
    private String payCode;
    private String payStatus;
    private OrderDto order;
    private Long paymentMethodId;

}

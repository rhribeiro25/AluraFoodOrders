package br.com.alurafood.orders.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;

    @NotNull
    @Positive
    private Integer qtt;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id", referencedColumnName = "id")
    private Order order;

}

package br.com.alurafood.orders.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;

    @NotNull
    @Size(max = 127)
    private String name;

    @NotNull
    @Size(max = 255)
    private String description;

}

package br.com.alurafood.orders.controller;

import br.com.alurafood.orders.dto.OrderDto;
import br.com.alurafood.orders.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

        @Autowired
        private OrderService orderService;

        @GetMapping()
        public List<OrderDto> list() {
            return orderService.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<OrderDto> orderById(@PathVariable @NotNull Long id) {
            OrderDto dto = orderService.findById(id);

            return  ResponseEntity.ok(dto);
        }

        @PostMapping()
        public ResponseEntity<OrderDto> create(@RequestBody @Valid OrderDto dto) {
            return ResponseEntity.ok(orderService.create(dto));

        }

        @PutMapping("/{id}")
        public ResponseEntity<OrderDto> edit(@RequestBody @Valid OrderDto dto, @PathVariable @NotNull Long id){
            return ResponseEntity.ok(orderService.update(dto, id));
        }
}

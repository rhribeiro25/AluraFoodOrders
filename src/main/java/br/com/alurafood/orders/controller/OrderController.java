package br.com.alurafood.orders.controller;

import br.com.alurafood.orders.dto.OrderDto;
import br.com.alurafood.orders.dto.OrderStatusDto;
import br.com.alurafood.orders.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
        public ResponseEntity<OrderDto> create(@RequestBody @Valid OrderDto dto, UriComponentsBuilder uriBuilder) {
            OrderDto pedidoRealizado = orderService.create(dto);

            URI endereco = uriBuilder.path("/orders/{id}").buildAndExpand(pedidoRealizado.getId()).toUri();

            return ResponseEntity.created(endereco).body(pedidoRealizado);

        }

        @PutMapping("/{id}/status")
        public ResponseEntity<OrderDto> edit(@PathVariable Long id, @RequestBody OrderStatusDto status){
           OrderDto dto = orderService.update(id, status);

            return ResponseEntity.ok(dto);
        }


        @PutMapping("/{id}/paid")
        public ResponseEntity<Void> approvePayment(@PathVariable @NotNull Long id) {
            orderService.approvePaymentOrder(id);

            return ResponseEntity.ok().build();

        }
}

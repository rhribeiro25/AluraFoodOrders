package br.com.alurafood.orders.controller;

import br.com.alurafood.orders.dto.ProductDto;
import br.com.alurafood.orders.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

        @Autowired
        private ProductService productService;

        @GetMapping()
        public List<ProductDto> list() {
            return productService.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<ProductDto> productById(@PathVariable @NotNull Long id) {
            ProductDto dto = productService.findById(id);

            return  ResponseEntity.ok(dto);
        }

        @PostMapping()
        public ResponseEntity<ProductDto> create(@RequestBody @Valid ProductDto dto) {
            return ResponseEntity.ok(productService.create(dto));

        }

        @PutMapping("/{id}")
        public ResponseEntity<ProductDto> edit(@RequestBody @Valid ProductDto dto, @PathVariable @NotNull Long id){
            return ResponseEntity.ok(productService.update(dto, id));
        }
}

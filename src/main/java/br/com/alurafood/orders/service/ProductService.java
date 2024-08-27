package br.com.alurafood.orders.service;

import br.com.alurafood.orders.dto.ProductDto;
import br.com.alurafood.orders.model.Product;
import br.com.alurafood.orders.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private final ModelMapper modelMapper;


    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(p -> modelMapper.map(p, ProductDto.class))
                .collect(Collectors.toList());
    }

    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(product, ProductDto.class);
    }

    public ProductDto create(ProductDto dto) {
        Product product = modelMapper.map(dto, Product.class);
        Product prodSave = productRepository.save(product);
        return modelMapper.map(prodSave, ProductDto.class);
    }

    public ProductDto update(ProductDto productDto, Long id) {

        if (productRepository.findProductById(id) == null) {
            throw new EntityNotFoundException();
        }
        Product product = modelMapper.map(productDto, Product.class);
        product.setId(id);
        productRepository.save(product);
        return modelMapper.map(product, ProductDto.class);
    }

}

package br.com.alurafood.orders.service;

import br.com.alurafood.orders.dto.OrderDto;
import br.com.alurafood.orders.dto.OrderStatusDto;
import br.com.alurafood.orders.model.Order;
import br.com.alurafood.orders.model.OrderStatus;
import br.com.alurafood.orders.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private final ModelMapper modelMapper;


    public List<OrderDto> findAll() {
        return repository.findAll().stream()
                .map(p -> modelMapper.map(p, OrderDto.class))
                .collect(Collectors.toList());
    }

    public OrderDto findById(Long id) {
        Order order = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(order, OrderDto.class);
    }

    public OrderDto create(OrderDto dto) {
        Order order = modelMapper.map(dto, Order.class);

        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.DONE);
        order.getOrderItems().forEach(item -> item.setOrder(order));
        Order salvo = repository.save(order);

        return modelMapper.map(order, OrderDto.class);
    }

    public OrderDto update(Long id, OrderStatusDto dto) {

        Order order = repository.findOrderById(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setOrderStatus(dto.getOrderStatus());
        repository.updateOrderStatus(dto.getOrderStatus(), order);
        return modelMapper.map(order, OrderDto.class);
    }

    public void approvePaymentOrder(Long id) {

        Order order = repository.findOrderById(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setOrderStatus(OrderStatus.PAID);
        repository.updateOrderStatus(OrderStatus.PAID, order);
    }
}

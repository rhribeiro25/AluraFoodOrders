package br.com.alurafood.orders.service;

import br.com.alurafood.orders.dto.OrderDto;
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
    private OrderRepository orderRepository;

    @Autowired
    private final ModelMapper modelMapper;


    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream()
                .map(p -> modelMapper.map(p, OrderDto.class))
                .collect(Collectors.toList());
    }

    public OrderDto findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(order, OrderDto.class);
    }

    public OrderDto create(OrderDto dto) {
        Order order = modelMapper.map(dto, Order.class);

        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.DONE);
        order.getOrderItems().forEach(item -> item.setOrder(order));
        Order orderSave = orderRepository.save(order);

        return modelMapper.map(orderSave, OrderDto.class);
    }

    public OrderDto update(OrderDto orderDto, Long id) {

        if (orderRepository.findOrderById(id) == null) {
            throw new EntityNotFoundException();
        }
        Order order = modelMapper.map(orderDto, Order.class);
        order.setId(id);
        orderRepository.save(order);
        return modelMapper.map(order, OrderDto.class);
    }

}

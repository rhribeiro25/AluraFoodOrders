package br.com.alurafood.orders.repository;

import br.com.alurafood.orders.model.Order;
import br.com.alurafood.orders.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Order o set o.orderStatus = :orderStatus where o = :order")
    void updateOrderStatus(OrderStatus orderStatus, Order order);

    @Query(value = "SELECT o from Order o LEFT JOIN FETCH o.orderItems where o.id = :id")
    Order findOrderById(Long id);


}

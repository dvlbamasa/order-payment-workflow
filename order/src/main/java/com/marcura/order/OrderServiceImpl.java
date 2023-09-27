package com.marcura.order;

import com.marcura.common.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 10:56 am
 */

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderMapper orderMapper;
    private final OrderRepository repository;

    @Override
    public void createOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        repository.save(order);
    }

    @Override
    public void rollBackCreateOrder(Long oderId) {
        Optional<Order> order = repository.findByOrderId(oderId);
        order.ifPresent(repository::delete);
    }
}

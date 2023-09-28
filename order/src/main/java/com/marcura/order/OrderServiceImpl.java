package com.marcura.order;

import com.marcura.common.OrderDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);


    private final OrderMapper orderMapper;
    private final OrderRepository repository;

    @Override
    public String createOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        Order orderPersisted = repository.save(order);
        return orderPersisted.getId().toString();
    }

    @Override
    public void rollBackCreateOrder(Long oderId) {
        LOGGER.info("PERFORMING ROLLBACK ON ORDER");
        Optional<Order> order = repository.findByOrderId(oderId);
        order.ifPresent(repository::delete);
    }
}

package com.marcura.order;

import com.marcura.common.OrderActivity;
import com.marcura.common.OrderDto;
import lombok.RequiredArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 11:27 am
 */
@RequiredArgsConstructor
public class OrderActivityImpl implements OrderActivity {

    private final OrderService orderService;

    @Override
    public void createOrder(OrderDto orderDto) {
        orderService.createOrder(orderDto);
    }
}

package com.marcura.order;

import com.marcura.common.OrderActivity;
import com.marcura.common.OrderDto;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 22/09/2023
 * Time: 8:44 pm
 */
public interface OrderService {

    void createOrder(OrderDto orderDto);
}

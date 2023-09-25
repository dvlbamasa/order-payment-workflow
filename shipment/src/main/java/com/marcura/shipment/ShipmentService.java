package com.marcura.shipment;

import com.marcura.common.OrderDto;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 3:52 pm
 */
public interface ShipmentService {

    void ship(OrderDto orderDto);
}

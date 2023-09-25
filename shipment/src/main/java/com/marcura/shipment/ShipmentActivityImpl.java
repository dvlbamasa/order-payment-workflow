package com.marcura.shipment;

import com.marcura.common.OrderDto;
import com.marcura.common.ShipmentActivity;
import lombok.RequiredArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 3:52 pm
 */
@RequiredArgsConstructor
public class ShipmentActivityImpl implements ShipmentActivity {

    private final ShipmentService shipmentService;

    @Override
    public void ship(OrderDto orderDto) {
        shipmentService.ship(orderDto);
    }
}

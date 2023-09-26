package com.marcura.shipment;

import com.marcura.common.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 3:53 pm
 */
@RequiredArgsConstructor
@Service
public class ShipmentServiceImpl implements ShipmentService{

    private final ShipmentMapper mapper;
    private final ShipmentRepository repository;

    @Override
    public String ship(OrderDto orderDto) {
        Shipment shipment = mapper.toEntity(orderDto);
        Shipment shipmentPersisted = repository.save(shipment);
        return shipmentPersisted.getId().toString();
    }
}

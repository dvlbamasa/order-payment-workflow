package com.marcura.shipment;

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
 * Time: 3:53 pm
 */
@RequiredArgsConstructor
@Service
public class ShipmentServiceImpl implements ShipmentService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipmentServiceImpl.class);

    private final ShipmentMapper mapper;
    private final ShipmentRepository repository;

    @Override
    public String ship(OrderDto orderDto) {
        Shipment shipment = mapper.toEntity(orderDto);
        Shipment shipmentPersisted = repository.save(shipment);
        return shipmentPersisted.getId().toString();
    }

    @Override
    public void rollbackShip(Long orderId) {
        LOGGER.info("PERFORMING ROLLBACK ON SHIPMENT");
        Optional<Shipment> shipment = repository.findByOrderId(orderId);
        shipment.ifPresent(repository::delete);
    }
}

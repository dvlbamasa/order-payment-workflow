package com.marcura.shipment;

import com.marcura.common.BaseModel;
import com.marcura.common.OrderShipmentType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 3:50 pm
 */
@Entity
@Table
@Setter
@Getter
public class Shipment extends BaseModel {
    private Long orderId;
    private Long productId;
    private OrderShipmentType orderShipmentType;
    private String address;
    private String customerName;
}

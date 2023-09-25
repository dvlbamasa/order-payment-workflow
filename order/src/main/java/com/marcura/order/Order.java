package com.marcura.order;

import com.marcura.common.BaseModel;
import com.marcura.common.OrderPaymentType;
import com.marcura.common.OrderShipmentType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 22/09/2023
 * Time: 8:45 pm
 */

@Entity
@Table(name = "ORDER_TRANSACTION")
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class Order extends BaseModel {
    private Long orderId;
    private Long productId;
    private BigDecimal amount;
    private BigDecimal shippingFee;
    @Enumerated(EnumType.STRING)
    private OrderShipmentType orderShipmentType;
    @Enumerated(EnumType.STRING)
    private OrderPaymentType orderPaymentType;
    private String address;
    private String customerName;
}

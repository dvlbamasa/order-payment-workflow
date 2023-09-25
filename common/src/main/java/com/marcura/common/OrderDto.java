package com.marcura.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 22/09/2023
 * Time: 4:04 pm
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class OrderDto {
    private Long orderId;
    private Long productId;
    private BigDecimal amount;
    private BigDecimal shippingFee;
    private OrderShipmentType orderShipmentType;
    private OrderPaymentType orderPaymentType;
    private String address;
    private String customerName;
}

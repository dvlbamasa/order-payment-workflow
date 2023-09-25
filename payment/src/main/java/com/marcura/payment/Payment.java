package com.marcura.payment;

import com.marcura.common.BaseModel;
import com.marcura.common.OrderPaymentType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 3:06 pm
 */
@Entity
@Table
@Getter
@Setter
public class Payment extends BaseModel {
    private Long orderId;
    private Long productId;
    private String paymentId;
    private BigDecimal amount;
    private BigDecimal shippingFee;
    @Enumerated(EnumType.STRING)
    private OrderPaymentType orderPaymentType;
}

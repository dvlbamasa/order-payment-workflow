package com.marcura.payment;

import com.marcura.common.OrderDto;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 3:18 pm
 */
public interface PaymentService {

    String debitPayment(OrderDto orderDto);
    void rollbackDebitPayment(Long orderId);
}

package com.marcura.payment;

import com.marcura.common.OrderDto;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 3:18 pm
 */
public interface PaymentService {

    void debitPayment(OrderDto orderDto);
}

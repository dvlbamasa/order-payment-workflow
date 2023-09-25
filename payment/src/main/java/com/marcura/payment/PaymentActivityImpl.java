package com.marcura.payment;

import com.marcura.common.OrderDto;
import com.marcura.common.PaymentActivity;
import lombok.RequiredArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 3:10 pm
 */

@RequiredArgsConstructor
public class PaymentActivityImpl implements PaymentActivity {

    private final PaymentService paymentService;
    @Override
    public void debitPayment(OrderDto orderDto) {
        paymentService.debitPayment(orderDto);
    }
}

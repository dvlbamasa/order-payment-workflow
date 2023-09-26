package com.marcura.payment;

import com.marcura.common.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 3:18 pm
 */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper paymentMapper;

    @Override
    public String debitPayment(OrderDto orderDto) {
        UUID paymentId = UUID.randomUUID();
        Payment payment = paymentMapper.toEntity(orderDto);
        payment.setPaymentId(paymentId.toString());
        Payment paymentPersisted = repository.save(payment);
        return paymentPersisted.getPaymentId();
    }
}

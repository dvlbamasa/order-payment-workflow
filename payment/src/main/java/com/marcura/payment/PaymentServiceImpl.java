package com.marcura.payment;

import com.marcura.common.OrderDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

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

    @Override
    public void rollbackDebitPayment(Long orderId) {
        LOGGER.info("PERFORMING ROLLBACK ON PAYMENT");
        Optional<Payment> payment = repository.findByOrderId(orderId);
        payment.ifPresent(repository::delete);
    }
}

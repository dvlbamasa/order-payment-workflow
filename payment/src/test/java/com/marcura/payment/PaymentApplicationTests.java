package com.marcura.payment;

import com.marcura.common.OrderDto;
import com.marcura.common.OrderPaymentType;
import com.marcura.common.OrderShipmentType;
import io.temporal.worker.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PaymentApplicationTests {

	@Mock
	private PaymentService paymentService;

	@MockBean
	private Worker createPaymentWorker;

	@Test
	public void testDebitPayment_success() {
		when(paymentService.debitPayment(any(OrderDto.class))).thenReturn("1234-PAYMENT-REF");
		String result = paymentService.debitPayment(generateDto());
		Assertions.assertEquals("1234-PAYMENT-REF", result);
	}

	private OrderDto generateDto() {
		OrderDto orderDto = new OrderDto();
		orderDto.setOrderId(1L);
		orderDto.setProductId(1L);
		orderDto.setOrderPaymentType(OrderPaymentType.CASH_ON_DELIVERY);
		orderDto.setAmount(BigDecimal.valueOf(1000));
		orderDto.setAddress("Manila");
		orderDto.setOrderShipmentType(OrderShipmentType.PICK_UP);
		orderDto.setShippingFee(BigDecimal.valueOf(1000));
		orderDto.setCustomerName("Customer");
		return orderDto;
	}

	@Test
	void contextLoads() {
	}

}

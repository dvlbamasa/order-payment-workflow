package com.marcura.order;

import com.marcura.common.OrderDto;
import com.marcura.common.OrderPaymentType;
import com.marcura.common.OrderShipmentType;
import com.marcura.common.ResponseDto;
import com.marcura.common.TaskQueue;
import com.marcura.payment.PaymentActivityImpl;
import com.marcura.payment.PaymentService;
import com.marcura.shipment.ShipmentActivityImpl;
import com.marcura.shipment.ShipmentService;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.testing.TestWorkflowEnvironment;
import io.temporal.testing.TestWorkflowRule;
import io.temporal.worker.Worker;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderApplicationTests {

	@Mock
	private OrderService orderService;
	@Mock
	private PaymentService paymentService;
	@Mock
	private ShipmentService shipmentService;

	@MockBean
	private Worker createOrderWorker;

	private TestWorkflowEnvironment testEnv;
	private Worker orderWorker;
	private Worker paymentWorker;
	private Worker shipmentWorker;
	private WorkflowClient client;

	@BeforeEach
	public void setUp() {
		testEnv = TestWorkflowEnvironment.newInstance();
		orderWorker = testEnv.newWorker(TaskQueue.ORDER_TASK_QUEUE.name());
		paymentWorker = testEnv.newWorker(TaskQueue.PAYMENT_TASK_QUEUE.name());
		shipmentWorker = testEnv.newWorker(TaskQueue.SHIPMENT_TASK_QUEUE.name());
		orderWorker.registerWorkflowImplementationTypes(OrderWorkflowImpl.class);
		client = testEnv.getWorkflowClient();
	}

	@AfterEach
	public void tearDown() {
		testEnv.close();
	}

	@Test
	public void testIntegrationOrder() {
		when(paymentService.debitPayment(any(OrderDto.class))).thenReturn("1234-PAYMENT-REF");
		when(shipmentService.ship(any(OrderDto.class))).thenReturn("1234-SHIP-REF");

		orderWorker.registerActivitiesImplementations(new OrderActivityImpl(orderService));
		paymentWorker.registerActivitiesImplementations(new PaymentActivityImpl(paymentService));
		shipmentWorker.registerActivitiesImplementations(new ShipmentActivityImpl(shipmentService));

		testEnv.start();
		OrderWorkflow workflow =
				client.newWorkflowStub(
						OrderWorkflow.class, WorkflowOptions.newBuilder()
								.setTaskQueue(TaskQueue.ORDER_TASK_QUEUE.name()).build());
		ResponseDto responseDto = workflow.createOrder(generateDto());
		Assertions.assertEquals("1234-SHIP-REF", responseDto.getShipmentId());
		Assertions.assertEquals("1234-PAYMENT-REF", responseDto.getPaymentTransactionId());
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

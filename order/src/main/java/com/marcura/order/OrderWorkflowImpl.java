package com.marcura.order;

import com.marcura.common.OrderActivity;
import com.marcura.common.OrderDto;
import com.marcura.common.PaymentActivity;
import com.marcura.common.ResponseDto;
import com.marcura.common.ShipmentActivity;
import io.temporal.failure.TemporalFailure;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 11:26 am
 */
public class OrderWorkflowImpl implements OrderWorkflow{

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderWorkflowImpl.class);

    private final OrderActivity orderActivity = Workflow.newActivityStub(OrderActivity.class,
            OrderActivity.GetActivityOptions());
    private final PaymentActivity paymentActivity = Workflow.newActivityStub(PaymentActivity.class,
            PaymentActivity.GetActivityOptions());
    private final ShipmentActivity shipmentActivity = Workflow.newActivityStub(ShipmentActivity.class,
            ShipmentActivity.GetActivityOptions());

    @Override
    public ResponseDto createOrder(OrderDto orderDto) {
        Saga saga = new Saga(new Saga.Options.Builder().build());
        try {
            LOGGER.info("Processing payment debit...");
            saga.addCompensation(paymentActivity::rollbackDebitPayment, orderDto.getOrderId());
            String paymentId = paymentActivity.debitPayment(orderDto);

            LOGGER.info("Shipping order...");
            saga.addCompensation(shipmentActivity::rollbackShip, orderDto.getOrderId());
            String shipmentId = shipmentActivity.ship(orderDto);

            LOGGER.info("Creating order...");
            saga.addCompensation(orderActivity::rollbackCreateOrder, orderDto.getOrderId());
            String orderId = orderActivity.createOrder(orderDto);
            LOGGER.info("Workflow finished!");

            ResponseDto responseDto = new ResponseDto();
            responseDto.setShipmentId(shipmentId);
            responseDto.setPaymentTransactionId(paymentId);
            responseDto.setOrderId(orderId);
            return responseDto;
        } catch (TemporalFailure failure) {
            saga.compensate();
            throw failure;
        }

    }
}

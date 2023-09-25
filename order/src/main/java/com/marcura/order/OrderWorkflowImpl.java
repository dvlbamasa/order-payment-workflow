package com.marcura.order;

import com.marcura.common.OrderActivity;
import com.marcura.common.OrderDto;
import com.marcura.common.PaymentActivity;
import com.marcura.common.ShipmentActivity;
import io.temporal.workflow.Workflow;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 11:26 am
 */
public class OrderWorkflowImpl implements OrderWorkflow{

    private final OrderActivity orderActivity = Workflow.newActivityStub(OrderActivity.class,
            OrderActivity.GetActivityOptions());
    private final PaymentActivity paymentActivity = Workflow.newActivityStub(PaymentActivity.class,
            PaymentActivity.GetActivityOptions());
    private final ShipmentActivity shipmentActivity = Workflow.newActivityStub(ShipmentActivity.class,
            ShipmentActivity.GetActivityOptions());
    @Override
    public void createOrder(OrderDto orderDto) {
        orderActivity.createOrder(orderDto);
        paymentActivity.debitPayment(orderDto);
        shipmentActivity.ship(orderDto);
    }
}

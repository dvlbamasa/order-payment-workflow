package com.marcura.order;

import com.marcura.common.OrderDto;
import io.temporal.client.WorkflowClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 10:41 am
 */

@RequiredArgsConstructor
@Component
public class OrderWorkflowOrchestrator {

    private final WorkflowClient workflowClient;

    public void createOrder(OrderDto orderDto) {
        OrderWorkflow orderWorkflow = workflowClient
                .newWorkflowStub(OrderWorkflow.class, OrderWorkflow.GetWorkflowOption());
        WorkflowClient.start(orderWorkflow::createOrder, orderDto);
    }
}

package com.marcura.order;

import com.marcura.common.OrderDto;
import com.marcura.common.ResponseDto;
import com.marcura.common.TaskQueue;
import io.temporal.client.WorkflowOptions;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 22/09/2023
 * Time: 6:03 pm
 */

@WorkflowInterface
public interface OrderWorkflow {

    @WorkflowMethod
    ResponseDto createOrder(OrderDto orderDto);

    static WorkflowOptions GetWorkflowOption() {
        return  WorkflowOptions.newBuilder()
                .setWorkflowId("OrderWorkflow-" + UUID.randomUUID().toString())
                .setTaskQueue(TaskQueue.ORDER_TASK_QUEUE.name())
                .build();
    }
}

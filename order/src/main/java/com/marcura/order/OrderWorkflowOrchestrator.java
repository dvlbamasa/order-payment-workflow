package com.marcura.order;

import com.marcura.common.OrderDto;
import com.marcura.common.ResponseDto;
import io.temporal.client.WorkflowClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

    public ResponseDto createOrder(OrderDto orderDto) {
        OrderWorkflow orderWorkflow = workflowClient
                .newWorkflowStub(OrderWorkflow.class, OrderWorkflow.GetWorkflowOption());
        ResponseDto responseDto = new ResponseDto();
        try {
            CompletableFuture<ResponseDto> response = WorkflowClient.execute(orderWorkflow::createOrder, orderDto);
            return response.get();
        } catch (ExecutionException | InterruptedException exception) {
            responseDto.setErrorMessage(exception.getMessage());
        }
        return responseDto;
    }
}

package com.marcura.order;

import com.marcura.common.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 22/09/2023
 * Time: 8:49 pm
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderWorkflowController {

    private final OrderWorkflowOrchestrator orderWorkflowOrchestrator;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto) {
        orderWorkflowOrchestrator.createOrder(orderDto);
        return ResponseEntity.ok("OK");
    }
}

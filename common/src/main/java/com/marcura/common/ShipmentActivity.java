package com.marcura.common;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;

import java.time.Duration;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 22/09/2023
 * Time: 5:25 pm
 */

@ActivityInterface
public interface ShipmentActivity {

    @ActivityMethod
    String ship(OrderDto orderDto);

    void rollbackShip(Long orderId);

    static ActivityOptions GetActivityOptions() {
        return ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(30))
                .setScheduleToCloseTimeout(Duration.ofSeconds(60))
                .setTaskQueue(TaskQueue.SHIPMENT_TASK_QUEUE.name())
                .setRetryOptions(GetRetryOptions())
                .build();
    }

    static RetryOptions GetRetryOptions() {
        return RetryOptions.newBuilder()
                .setMaximumAttempts(2)
                .build();
    }
}

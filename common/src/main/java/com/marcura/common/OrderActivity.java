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
 * Time: 5:24 pm
 */

@ActivityInterface
public interface OrderActivity {

    @ActivityMethod
    String createOrder(OrderDto orderDto);

    void rollbackCreateOrder(Long orderId);

    static ActivityOptions GetActivityOptions() {
        return ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(30))
                .setScheduleToCloseTimeout(Duration.ofSeconds(60))
                .setTaskQueue(TaskQueue.ORDER_TASK_QUEUE.name())
                .setRetryOptions(GetRetryOptions())
                .build();
    }

    static RetryOptions GetRetryOptions() {
        return RetryOptions.newBuilder()
                .setMaximumAttempts(2)
                .build();
    }
}

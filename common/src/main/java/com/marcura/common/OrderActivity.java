package com.marcura.common;

import io.temporal.activity.ActivityInterface;
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
    void createOrder(OrderDto orderDto);

    static ActivityOptions GetActivityOptions() {
        return ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofMinutes(2))
                .setTaskQueue(TaskQueue.ORDER_TASK_QUEUE.name())
                .setRetryOptions(GetRetryOptions())
                .build();
    }

    static RetryOptions GetRetryOptions() {
        return RetryOptions.newBuilder()
                .setMaximumAttempts(3)
                .build();
    }
}

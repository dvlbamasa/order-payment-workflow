package com.marcura.common;

import io.temporal.activity.ActivityInterface;
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
public interface PaymentActivity {
    void debitPayment(OrderDto orderDto);

    static ActivityOptions GetActivityOptions() {
        return ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofMinutes(2))
                .setRetryOptions(GetRetryOptions())
                .build();
    }

    static RetryOptions GetRetryOptions() {
        return RetryOptions.newBuilder()
                .setMaximumAttempts(3)
                .build();
    }
}

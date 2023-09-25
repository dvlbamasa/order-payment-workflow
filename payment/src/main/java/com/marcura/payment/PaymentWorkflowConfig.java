package com.marcura.payment;

import com.marcura.common.TaskQueue;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 3:10 pm
 */
@Configuration
public class PaymentWorkflowConfig {

    @Bean(name="createPaymentWorker")
    @DependsOn("createWorkerFactory")
    public Worker createWorker(WorkerFactory workerFactory, PaymentService orderService){
        Worker worker = workerFactory.newWorker(TaskQueue.PAYMENT_TASK_QUEUE.name());
        worker.registerActivitiesImplementations(new PaymentActivityImpl(orderService));
        workerFactory.start();
        return worker;
    }
}

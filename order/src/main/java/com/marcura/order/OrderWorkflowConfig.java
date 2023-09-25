package com.marcura.order;

import com.marcura.common.TaskQueue;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 22/09/2023
 * Time: 8:44 pm
 */

@Configuration
public class OrderWorkflowConfig {

    @Bean(name="createOrderWorker")
    @DependsOn("createWorkerFactory")
    public Worker createWorker(WorkerFactory workerFactory, OrderServiceImpl orderService){
        Worker worker = workerFactory.newWorker(TaskQueue.ORDER_TASK_QUEUE.name());
        worker.registerWorkflowImplementationTypes(OrderWorkflowImpl.class);
        worker.registerActivitiesImplementations(new OrderActivityImpl(orderService));
        workerFactory.start();
        return worker;
    }

}

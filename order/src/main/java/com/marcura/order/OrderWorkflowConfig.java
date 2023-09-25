package com.marcura.order;

import com.marcura.common.TaskQueue;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
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

    private final static String TARGET_ENDPOINT = "127.0.0.1:7233";

    private final static String NAMESPACE = "default";

    @Bean
    public WorkflowServiceStubs workflowServiceStubs() {
        return WorkflowServiceStubs.newServiceStubs(WorkflowServiceStubsOptions
                .newBuilder()
                .setTarget(TARGET_ENDPOINT)
                .build());
    }

    @Bean
    public WorkflowClient workflowClient(WorkflowServiceStubs workflowServiceStubs) {
        return WorkflowClient.newInstance(workflowServiceStubs,
                WorkflowClientOptions.newBuilder().setNamespace(NAMESPACE).build());
    }

    @Bean(name="createWorkerFactory")
    public WorkerFactory createWorkerFactory(WorkflowClient workflowClient){
        return WorkerFactory.newInstance(workflowClient);
    }

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

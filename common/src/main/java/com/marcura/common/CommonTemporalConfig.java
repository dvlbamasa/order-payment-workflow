package com.marcura.common;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.WorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 25/09/2023
 * Time: 2:41 pm
 */
@Configuration
public class CommonTemporalConfig {

    private final static String TARGET_ENDPOINT = "127.0.0.1:7233";
    private final static String NAMESPACE = "default";

    @Bean
    public WorkflowServiceStubs workflowServiceStubs() {
        return WorkflowServiceStubs.newServiceStubs(WorkflowServiceStubsOptions
                .newBuilder()
                .setTarget(TARGET_ENDPOINT)
                .build());
    }

    @Bean(name="workflowClient")
    public WorkflowClient workflowClient(WorkflowServiceStubs workflowServiceStubs) {
        return WorkflowClient.newInstance(workflowServiceStubs,
                WorkflowClientOptions.newBuilder().setNamespace(NAMESPACE).build());
    }

    @Bean(name="createWorkerFactory")
    public WorkerFactory createWorkerFactory(WorkflowClient workflowClient){
        return WorkerFactory.newInstance(workflowClient);
    }
}

package com.marcura.shipment;

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
 * Time: 3:56 pm
 */
@Configuration
public class ShipmentWorkflowConfig {

    @Bean(name="createShipmentWorker")
    @DependsOn("createWorkerFactory")
    public Worker createWorker(WorkerFactory workerFactory, ShipmentService orderService){
        Worker worker = workerFactory.newWorker(TaskQueue.SHIPMENT_TASK_QUEUE.name());
        worker.registerActivitiesImplementations(new ShipmentActivityImpl(orderService));
        workerFactory.start();
        return worker;
    }
}

package com.marcura.common;

import io.temporal.api.schedule.v1.SchedulePatch;
import io.temporal.api.workflowservice.v1.DeleteScheduleRequest;
import io.temporal.api.workflowservice.v1.DescribeScheduleRequest;
import io.temporal.api.workflowservice.v1.DescribeScheduleResponse;
import io.temporal.api.workflowservice.v1.ListSchedulesRequest;
import io.temporal.api.workflowservice.v1.ListSchedulesResponse;
import io.temporal.api.workflowservice.v1.PatchScheduleRequest;
import io.temporal.client.WorkflowClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 03/10/2023
 * Time: 1:49 pm
 */
@Service
@RequiredArgsConstructor
public class TemporalScheduleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemporalScheduleService.class);
    private final WorkflowClient workflowClient;
    private static final String NAMESPACE = "default";

    public void pauseSchedule(String scheduleId, String reason) {
        workflowClient.getWorkflowServiceStubs().blockingStub()
                .patchSchedule(PatchScheduleRequest.newBuilder()
                        .setScheduleId(scheduleId)
                        .setNamespace(NAMESPACE)
                        .setPatch(SchedulePatch.newBuilder()
                                .setPause(reason).build()).build());
    }

    public void unpauseSchedule(String scheduleId, String reason) {
        workflowClient.getWorkflowServiceStubs().blockingStub()
                .patchSchedule(PatchScheduleRequest.newBuilder()
                        .setScheduleId(scheduleId)
                        .setNamespace(NAMESPACE)
                        .setPatch(SchedulePatch.newBuilder()
                                .setUnpause(reason).build()).build());
    }

    public void deleteSchedule(String scheduleId) {
        workflowClient.getWorkflowServiceStubs()
                .blockingStub().deleteSchedule(DeleteScheduleRequest.newBuilder()
                        .setScheduleId(scheduleId)
                        .setNamespace(NAMESPACE)
                        .build());
    }

    public void listSchedule() {
        ListSchedulesResponse response = workflowClient.getWorkflowServiceStubs().blockingStub()
                .listSchedules(ListSchedulesRequest.newBuilder().setNamespace(NAMESPACE).build());

        LOGGER.info("SCHEDULES SIZE: " + response.getSchedulesList().size());
    }

    public void describeSchedule(String scheduleId) {
        DescribeScheduleResponse response = workflowClient.getWorkflowServiceStubs().blockingStub()
                .describeSchedule(DescribeScheduleRequest.newBuilder()
                        .setScheduleId(scheduleId)
                        .setNamespace(NAMESPACE)
                        .build());
        LOGGER.info("DESCRIBE WORKFLOW: " + response.getSchedule());
    }

}

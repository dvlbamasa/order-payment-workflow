package com.marcura.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.ByteString;
import io.temporal.api.common.v1.Payload;
import io.temporal.api.common.v1.Payloads;
import io.temporal.api.common.v1.WorkflowType;
import io.temporal.api.schedule.v1.IntervalSpec;
import io.temporal.api.schedule.v1.Schedule;
import io.temporal.api.schedule.v1.ScheduleAction;
import io.temporal.api.schedule.v1.ScheduleSpec;
import io.temporal.api.taskqueue.v1.TaskQueue;
import io.temporal.api.workflow.v1.NewWorkflowExecutionInfo;
import io.temporal.api.workflowservice.v1.CreateScheduleRequest;
import io.temporal.client.WorkflowClient;
import io.temporal.common.converter.EncodingKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 03/10/2023
 * Time: 11:53 am
 */

@Component
@RequiredArgsConstructor
public class TemporalScheduleComponent {

    private static final String WORKFLOW = "SCHEDULED_WORKFLOW";
    private static final String TASK_QUEUE = "SCHEDULED_TASK_QUEUE";
    private static final String WORKFLOW_ID = WORKFLOW + "-" + UUID.randomUUID();
    private static final String NAMESPACE = "default";
    private static final String REQUEST_ID = "test-request-ID";
    private final WorkflowClient workflowClient;

    public void createTemporalSchedule(String scheduleId) throws JsonProcessingException {
        ScheduleSpec.Builder interval = ScheduleSpec.newBuilder().addInterval(IntervalSpec.newBuilder()
                .setInterval(com.google.protobuf.Duration.newBuilder().setSeconds(5).build())
                .build());
        //create Payload
        ScheduleRequest request = ScheduleRequest.builder().name("TEST").build();
        String stringRequest = new ObjectMapper().writeValueAsString(request);
        ByteString byteStringRequest = ByteString.copyFromUtf8(stringRequest);
        Payloads payloads = Payloads.newBuilder().addPayloads(
                        Payload.newBuilder()
                                .putMetadata(EncodingKeys.METADATA_ENCODING_KEY, ByteString.copyFrom("json/plain", StandardCharsets.UTF_8))
                                .setData(byteStringRequest).build())
                .build();
        //create workflow
        NewWorkflowExecutionInfo workflowExecutionInfo =
                NewWorkflowExecutionInfo.newBuilder()
                        .setWorkflowType(WorkflowType.newBuilder().setName(WORKFLOW).build())
                        .setTaskQueue(TaskQueue.newBuilder().setName(TASK_QUEUE).build())
                        .setWorkflowId(WORKFLOW_ID)
                        .setInput(payloads)
                        .build();
        //CREATE
        workflowClient.getWorkflowServiceStubs()
                .blockingStub()
                .createSchedule(
                        CreateScheduleRequest.newBuilder()
                                .setScheduleId(scheduleId)
                                .setNamespace(NAMESPACE)
                                .setRequestId(REQUEST_ID)
                                .setSchedule(
                                        Schedule.newBuilder()
                                                .setSpec(interval.build())
                                                .setAction(ScheduleAction.newBuilder().setStartWorkflow(workflowExecutionInfo).build())
                                                .build())
                                .build());
    }
}

package com.marcura.order;

import com.marcura.common.TemporalScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 03/10/2023
 * Time: 1:47 pm
 */
@RestController
@RequestMapping("/api/temporal/schedule")
@RequiredArgsConstructor
public class TemporalScheduleController {

    private final TemporalScheduleService temporalScheduleService;

    @GetMapping("/pause")
    public ResponseEntity<String> pauseSchedule(@RequestParam("scheduleId") String scheduleId,
                                        @RequestParam("reason") String reason) {
        temporalScheduleService.pauseSchedule(scheduleId, reason);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/unpause")
    public ResponseEntity<String> unpauseSchedule(@RequestParam("scheduleId") String scheduleId,
                                                  @RequestParam("reason") String reason) {
        temporalScheduleService.unpauseSchedule(scheduleId, reason);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSchedule(@RequestParam("scheduleId") String scheduleId) {
        temporalScheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/describe")
    public ResponseEntity<String> describeSchedle(@RequestParam("scheduleId") String scheduleId) {
        temporalScheduleService.describeSchedule(scheduleId);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/list")
    public ResponseEntity<String> listSchedules() {
        temporalScheduleService.listSchedule();
        return ResponseEntity.ok("OK");
    }
}

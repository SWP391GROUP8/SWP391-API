package swp391.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.dto.schedule.CreateScheduleDto;
import swp391.entity.Course;
import swp391.entity.Schedule;
import swp391.service.ScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    private ScheduleService scheduleService;
    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService=scheduleService;
    }
    @PostMapping
    public ResponseEntity create(@RequestBody CreateScheduleDto dto) {
        if (scheduleService.isExisted(dto.getId())) {
            return ResponseEntity.badRequest().body("Schedule Id is duplicated");
        }
        Schedule schedule = scheduleService.create(dto);

        return ResponseEntity.ok().body(schedule);
    }

    @GetMapping
    private ResponseEntity getAll() {
        List<Schedule> scheduleList = scheduleService.getAll();
        return ResponseEntity.ok().body(scheduleList);
    }

    @PutMapping
    private ResponseEntity update(@RequestBody CreateScheduleDto dto) {
        Schedule schedule = scheduleService.update(dto);
        return ResponseEntity.ok().body(schedule);
    }

    @DeleteMapping()
    private ResponseEntity delete(@RequestParam String id) {
        scheduleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-by-id")
    private ResponseEntity getById(@RequestParam String id) {
        Schedule schedule = scheduleService.getById(id);
        return ResponseEntity.ok().body(schedule);
    }
}

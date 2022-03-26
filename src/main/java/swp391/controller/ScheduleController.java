package swp391.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.dto.schedule.AddUserDto;
import swp391.dto.schedule.CreateScheduleDto;

import swp391.entity.Schedule;
import swp391.service.ScheduleService;
import swp391.service.UserService;


import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    private ScheduleService scheduleService;
    private UserService userService;

    public ScheduleController(ScheduleService scheduleService, UserService userService) {
        this.scheduleService = scheduleService;
        this.userService = userService;
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

    @GetMapping("/get-by-course-id")
    private ResponseEntity getByCourseId(@RequestParam String id) {
        List<Schedule> schedule = scheduleService.getByCourseId(id);
        return ResponseEntity.ok().body(schedule);
    }

    @PostMapping("/add-user")
    public Object addUserToSchedule(@RequestBody AddUserDto dto) {
        if (!scheduleService.isExisted(dto.getScheduleId())) {
            return ResponseEntity.badRequest().body("Schedule Id is not found");
        }
        for (int i = 0; i < dto.getUserIdList().size(); i++) {
            String userId = dto.getUserIdList().get(i);
            if (userService.findByScheduleIdAndUserId(dto.getScheduleId(), userId)) {
                return ResponseEntity.badRequest().body("Email: " + userId + " is existed in this schedule.");
            }
        }
        scheduleService.addUser(dto);
        return ResponseEntity.ok().body("Add successful");
    }

    @PostMapping("/remove-user")
    public Object removeUserToSchedule(@RequestBody AddUserDto dto) {
        if (!scheduleService.isExisted(dto.getScheduleId())) {
            return ResponseEntity.badRequest().body("Schedule Id is not found");
        }
        for (int i = 0; i < dto.getUserIdList().size(); i++) {
            String userId = dto.getUserIdList().get(i);
            if (!userService.findByScheduleIdAndUserId(dto.getScheduleId(), userId)) {
                return ResponseEntity.badRequest().body("Email " + userId + " not found in this schedule");
            }
        }
        scheduleService.removeUser(dto);
        return ResponseEntity.ok().body("Remove successful");
    }

    @PutMapping("/approve")
    public Object approve(@RequestParam String scheduleId) {
        scheduleService.approve(scheduleId);
        return ResponseEntity.ok().body("Successful");
    }

    @GetMapping("/get-by-email")
    private Object getByUserId(@RequestParam String email) {
        List<Schedule> scheduleList = scheduleService.getByUserId(email);
        return ResponseEntity.ok().body(scheduleList);
    }
}

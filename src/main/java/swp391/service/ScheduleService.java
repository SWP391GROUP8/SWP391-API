package swp391.service;

import swp391.dto.schedule.CreateScheduleDto;
import swp391.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    boolean isExisted(String id);

    Schedule create(CreateScheduleDto dto);

    List<Schedule> getAll();

    Schedule update(CreateScheduleDto dto);

    void delete(String id);

    Schedule getById(String id);
}

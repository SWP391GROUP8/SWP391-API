package swp391.service.impl;

import org.springframework.stereotype.Service;
import swp391.dto.schedule.AddUserDto;
import swp391.dto.schedule.CreateScheduleDto;

import swp391.entity.Schedule;
import swp391.entity.User;
import swp391.repository.ScheduleRepository;
import swp391.repository.UserRepository;
import swp391.service.ScheduleService;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleRepository scheduleRepository;
    private UserRepository userRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository,UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository=userRepository;
    }

    @Override
    public boolean isExisted(String id) {
        return scheduleRepository.existsById(id);
    }

    @Override
    public Schedule create(CreateScheduleDto dto) {
        Schedule schedule = new Schedule();
        schedule.setId(dto.getId());
        schedule.setTitle(dto.getTitle());
        schedule.setContent(dto.getContent());
        schedule.setStartTime(dto.getStartTime());
        schedule.setEndTime(dto.getEndTime());
        schedule.setCreatedBy(dto.getCreatedBy());
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule update(CreateScheduleDto dto) {
        Schedule schedule = scheduleRepository.getById(dto.getId());
        schedule.setTitle(dto.getTitle());
        schedule.setContent(dto.getContent());
        schedule.setStartTime(dto.getStartTime());
        schedule.setEndTime(dto.getEndTime());
        schedule.setCreatedBy(dto.getCreatedBy());
        return scheduleRepository.save(schedule);
    }

    @Override
    public void delete(String id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public Schedule getById(String id) {
        return scheduleRepository.getById(id);
    }

    @Override
    public void addUser(AddUserDto dto) {
        Schedule schedule = scheduleRepository.getById(dto.getScheduleId());

        for (int i = 0; i < dto.getUserIdList().size(); i++) {
            User user = userRepository.getById(dto.getUserIdList().get(i));
            schedule.addUser(user);

        }
        scheduleRepository.save(schedule);
    }
}

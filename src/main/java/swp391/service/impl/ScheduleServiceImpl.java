package swp391.service.impl;

import org.springframework.stereotype.Service;
import swp391.dto.schedule.AddUserDto;
import swp391.dto.schedule.CreateScheduleDto;

import swp391.entity.Schedule;
import swp391.entity.User;
import swp391.repository.CourseRepository;
import swp391.repository.ScheduleRepository;
import swp391.repository.UserRepository;
import swp391.service.ScheduleService;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public ScheduleServiceImpl(CourseRepository courseRepository,ScheduleRepository scheduleRepository,UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository=userRepository;
        this.courseRepository=courseRepository;
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
        schedule.setStatus(dto.getStatus());
        schedule.setCourse(courseRepository.getById(dto.getCourseId()));
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
        schedule.setStatus(dto.getStatus());
        schedule.setCourse(courseRepository.getById(dto.getCourseId()));
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

    @Override
    public void approve(String scheduleId) {
        Schedule schedule = scheduleRepository.getById(scheduleId);
        schedule.setStatus("Approved");
        scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> getByCourseId(String id) {
        return scheduleRepository.getAllByCourse(id);
    }

    @Override
    public List<Schedule> getByUserId(String email) {
        return scheduleRepository.getByUserId(email);
    }

    @Override
    public void removeUser(AddUserDto dto) {
        Schedule schedule = scheduleRepository.getById(dto.getScheduleId());
        for (int i = 0; i < dto.getUserIdList().size(); i++) {
            User user = userRepository.getById(dto.getUserIdList().get(i));
            user.removeSchedule(schedule);
            userRepository.save(user);
        }
//        User user = userRepository.getById(dto.getUserIdList());
//        user.removeSchedule(schedule);

//        userRepository.save(user);
//        scheduleRepository.save(schedule);
    }
}

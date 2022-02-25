package swp391.service.impl;

import org.springframework.stereotype.Service;
import swp391.dto.jobPosting.ModifiJobPostingDto;
import swp391.entity.JobPosting;
import swp391.repository.JobPostingRepository;
import swp391.repository.UserRepository;
import swp391.service.JobPostingService;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobPostingServiceImpl implements JobPostingService {
    private JobPostingRepository jobPostingRepository;
    private UserRepository userRepository;
    public JobPostingServiceImpl(JobPostingRepository jobPostingRepository,UserRepository userRepository){
        this.jobPostingRepository=jobPostingRepository;
        this.userRepository=userRepository;
    }
    @Override
    public List<JobPosting> getAll() {
        return jobPostingRepository.findAll();
    }

    @Override
    public boolean isExisted(String id) {
        return jobPostingRepository.existsById(id);
    }

    @Override
    public JobPosting create(ModifiJobPostingDto dto) {
        JobPosting jobPosting = new JobPosting();
        jobPosting.setId(dto.getId());
        jobPosting.setTitle(dto.getTitle());
        jobPosting.setContent(dto.getContent());
        jobPosting.setCreateDate(LocalDate.now());
        jobPosting.setStatus(dto.getStatus());
        jobPosting.setCity(dto.getCity());
        jobPosting.setAddress(dto.getAddress());
        jobPosting.setMail(dto.getMail());
        jobPosting.setPhoneNumber(dto.getPhoneNumber());
        jobPosting.setUser(userRepository.getById(dto.getUserId()));
        return jobPostingRepository.save(jobPosting);
    }

    @Override
    public JobPosting update(String id, ModifiJobPostingDto dto) {
        JobPosting jobPosting =jobPostingRepository.getById(id);
        jobPosting.setTitle(dto.getTitle());
        jobPosting.setContent(dto.getContent());
        jobPosting.setCreateDate(LocalDate.now());
        jobPosting.setStatus(dto.getStatus());
        jobPosting.setCity(dto.getCity());
        jobPosting.setAddress(dto.getAddress());
        jobPosting.setMail(dto.getMail());
        jobPosting.setPhoneNumber(dto.getPhoneNumber());
        jobPosting.setUser(userRepository.getById(dto.getUserId()));
        return jobPostingRepository.save(jobPosting);
    }

    @Override
    public void delete(String id) {
        jobPostingRepository.deleteById(id);
    }

    @Override
    public JobPosting getById(String id) {
        return jobPostingRepository.getById(id);
    }
}

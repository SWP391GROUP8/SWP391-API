package swp391.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.dto.jobPosting.ModifiJobPostingDto;
import swp391.entity.JobPosting;
import swp391.service.JobPostingService;
import swp391.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/job-posting")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JobPostingController {
    private JobPostingService jobPostingService;
    private UserService userService;


    public JobPostingController(JobPostingService jobPostingService, UserService userService) {
        this.jobPostingService = jobPostingService;
        this.userService = userService;
    }

    @GetMapping
    private ResponseEntity getAll() {
        List<JobPosting> jobPostingList = jobPostingService.getAll();
        return ResponseEntity.ok().body(jobPostingList);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ModifiJobPostingDto dto) {
        if (jobPostingService.isExisted(dto.getId())) {
            return ResponseEntity.badRequest().body("Id is duplicated");
        }
        if (!userService.isExisted(dto.getUserId())) {
            return ResponseEntity.badRequest().body("Email not found");
        }
        JobPosting jobPosting = jobPostingService.create(dto);

        return ResponseEntity.ok().body(jobPosting);
    }
    @PutMapping
    private ResponseEntity update(@RequestBody ModifiJobPostingDto dto) {
        JobPosting jobPosting = jobPostingService.update(dto.getId(), dto);
        return ResponseEntity.ok().body(jobPosting);
    }

    @DeleteMapping()
    private ResponseEntity delete(@RequestParam String id) {
        jobPostingService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-by-id")
    private ResponseEntity getById(@RequestParam String id) {
        JobPosting jobPosting = jobPostingService.getById(id);
        return ResponseEntity.ok().body(jobPosting);
    }
}

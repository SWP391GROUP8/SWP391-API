package swp391.entity;

import com.amazonaws.services.codegurureviewer.model.Reaction;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Columns;
import org.springframework.format.annotation.DateTimeFormat;
import swp391.entity.util.DateUtils;
import swp391.entity.util.UserBlog;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
@Table(name = "tbl_user")
public class User {
    @Id
    @Column
    private String email;
    @Column
    private String name;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate birthDay;
    @Column
    private String phone;
    @Column
    private String address;
    @Column
//    @JsonIgnore
    private String password;
    @Column
    private String status;

    // relationship role - user: 1-N
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    // relationship user - schedule: N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_schedule", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "schedule_id"))
    private Set<Schedule> schedules = new HashSet<>();

    // relationship user - comment: 1 - N
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();

    //relationship user - job posting: 1 - N
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<JobPosting> jobPostings = new HashSet<>();

    // relationship user - blog: N-N
    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, orphanRemoval = true)
    @JsonIgnore
    private List<UserBlog> userBlogList = new ArrayList<>();

    // relationship user - file: 1-N
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<File> files = new HashSet<>();

    //
    public void addBlog(Blog blog, Boolean isReaction) {
        UserBlog userBlog = new UserBlog(this, blog, isReaction);
        userBlogList.add(userBlog);
        blog.getUserBlogList().add(userBlog);
    }
    public void removeSchedule(Schedule schedule) {
        schedules.remove(schedule);
        schedule.getUsers().remove(this);

    }

}

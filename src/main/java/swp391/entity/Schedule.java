package swp391.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import swp391.entity.util.DateUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
@Entity
@Table
public class Schedule {
    @Column
    @Id
    private String id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate startTime;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate endTime;
    @Column
    private String createdBy;
    @Column
    private String status;

    // relationship user - schedule: N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "schedules",fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();


    // relationship course - schedule: 1 - N
//    @JsonIgnore
//    @Builder.Default
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name = "schedule_course", joinColumns = @JoinColumn(name = "schedule_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
//    private Set<Course>  courses= new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    //helper
    public void addUser(User user) {
        users.add(user);
        user.getSchedules().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getSchedules().remove(this);

    }
}

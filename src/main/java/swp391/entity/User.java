package swp391.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Columns;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
@Table(name = "table_user")
public class User {
    @Id
    @Column
    private String email;
    @Column
    private String name;
    @Column
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDay;
    @Column
    private String phone;
    @Column
    private String address;
    @Column
    @JsonIgnore
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

}

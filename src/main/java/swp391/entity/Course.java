package swp391.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
@Entity
@Table
public class Course {
    @Column
    @Id
    private String id;
    @Column
    private String author;
    @Column
    private String name;
    @Column
    private String code;
    @Column
    private String status;


    // relationship course - schedule: 1 - N
//    @JsonIgnore
//    @Builder.Default
//    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
//    private Set<Schedule> schedules = new HashSet<>();
    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<Schedule> schedules = new HashSet<>();

    //relationship course - course_qa: 1 - N
    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<Course_QA> course_qa = new HashSet<>();

    // relationship course - course_Resource: 1 - N
    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<Course_Resource> course_resources = new HashSet<>();

    // relationship course - preCourse: N-N
//    @JsonIgnore
//    @Builder.Default
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name = "course_preCourse", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "preCourse_id"))
//    private Set<PreCourse> preCourses = new HashSet<>();

    //  môn tiên quyết course - preCourse : N-N (self-referencing)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
   @JsonIgnore
    @JoinTable(name = "CourseWPreCourse",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "preCourse_id")})
    private Set<Course> courses = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private Set<Course> preCourse = new HashSet<>();

    public void addPreCourse(Course preCourse){
        courses.add(preCourse);
        preCourse.getPreCourse().add(this);
    }

}
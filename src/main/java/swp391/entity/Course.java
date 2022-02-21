package swp391.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
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

    // relationship schedule - course: N - N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private Set<Schedule> schedules = new HashSet<>();

    //relationship course - course_qa: 1 - N
    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<Course_QA> course_qa = new HashSet<>();

    // relationship course - course_Resource: 1 - N
    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<Course_Resource> course_resources = new HashSet<>();

    // relationship course - file: N - N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "course_file", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "file_id"))
    private Set<File> files = new HashSet<>();

}

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

//@Builder
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
//@Entity
//@Table(name = "tbl_preCourse")
public class PreCourse { // môn tiên quyết
//    @Column
//    @Id
//    private String id;
//    @Column
//    private String author;
//    @Column
//    private String name;
//    @Column
//    private String code;
//    @Column
//    private String status;

    // relationship course - preCourse: N-N
//    @JsonIgnore
//    @Builder.Default
//    @ManyToMany(mappedBy = "preCourses",fetch = FetchType.LAZY)
//    private Set<Course> courses = new HashSet<>();
}

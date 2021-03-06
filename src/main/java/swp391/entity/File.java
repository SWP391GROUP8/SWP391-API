package swp391.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.apache.http.annotation.Contract;
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
@Table(name = "tbl_file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate createDAte;
    @Column
    private String path;
    //relationship file - course_Resource: 1-1
    @OneToOne(mappedBy = "file", fetch = FetchType.LAZY)
    @JsonIgnore
    private Course_Resource course_resource;
    // relationship user - file: 1-N
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}

package swp391.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "table_blog")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
public class Blog {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String status;
    @Column
    private int reaction;
    @Column
    private String author;
    // relationship blog - comment: 1 - N
    @OneToMany(mappedBy = "blog")
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();
}

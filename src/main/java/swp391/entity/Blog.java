package swp391.entity;

import com.amazonaws.services.codegurureviewer.model.Reaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import swp391.entity.util.UserBlog;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "tbl_blog")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Blog {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column

    private String title;
    @Column(columnDefinition = "TEXT")
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
//

    @OneToMany(
            mappedBy = "blog",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<UserBlog> userBlogList = new ArrayList<>();

    //

}

package swp391.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import swp391.entity.util.DateUtils;
import swp391.entity.util.UserBlogId;
import swp391.repository.BlogRepository;
import swp391.repository.Course_qaRepository;
import swp391.repository.UserRepository;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "table_comment")
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate createDate;

    // relationship user - comment: 1 - N
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // relationship blog - comment: 1 - N
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id")
    private Blog blog;

    // relationship course_qa - comment: 1 - N
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_qa_id")
    private Course_QA course_qa;

    // comment - subcomment : 1 - n (self-referencing)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Comment comment;

    @OneToMany(mappedBy = "comment")
    @JsonIgnore
    private Set<Comment> subComment = new HashSet<>();

    // helper
    public Comment addSubComment(String commentName) {
        Comment subComment = new Comment();
        subComment.setContent(commentName);
        subComment.setCreateDate(LocalDate.now());
        this.subComment.add(subComment);
        subComment.setComment(this);
        return subComment;
    }


}

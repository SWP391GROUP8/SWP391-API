package swp391.entity.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jmx.export.annotation.ManagedNotifications;
import swp391.entity.Blog;
import swp391.entity.User;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_userBlog")
public class UserBlog {
    @EmbeddedId
    private UserBlogId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("email")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("blogId")
    private Blog blog;

    private Boolean isReaction;


    public UserBlog(User user, Blog blog,Boolean isReaction) {
        this.user=user;
        this.blog=blog;
        this.id = new UserBlogId(user.getEmail(),blog.getId());

        this.isReaction=isReaction;
    }
}

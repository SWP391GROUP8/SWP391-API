package swp391.service;

import swp391.dto.blog.ModifiBlogDto;
import swp391.dto.blog.UpdateBlogDto;
import swp391.dto.course.CreateCourseDto;
import swp391.entity.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> getAll();

    boolean isExisted(Long id);

    Blog create(ModifiBlogDto dto);

    Blog update(Long id, UpdateBlogDto dto);

    void delete(Long id);

    Blog getById(Long id);

    int reaction(Long blogId);

    List<Blog> ranking();
}

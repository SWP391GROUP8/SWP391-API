package swp391.service;

import swp391.dto.blog.ModifiBlogDto;
import swp391.dto.course.CreateCourseDto;
import swp391.entity.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> getAll();

    boolean isExisted(String id);

    Blog create(ModifiBlogDto dto);

    Blog update(String id, ModifiBlogDto dto);

    void delete(String id);

    Blog getById(String id);

    int reaction(String blogId);
}

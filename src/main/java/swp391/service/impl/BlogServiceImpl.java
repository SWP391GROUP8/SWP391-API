package swp391.service.impl;

import org.springframework.stereotype.Service;
import swp391.dto.blog.ModifiBlogDto;

import swp391.entity.Blog;
import swp391.repository.BlogRepository;
import swp391.service.BlogService;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private BlogRepository blogRepository;
    public BlogServiceImpl(BlogRepository blogRepository){
        this.blogRepository=blogRepository;
    }
    @Override
    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    @Override
    public boolean isExisted(String id) {
        return blogRepository.existsById(id);
    }

    @Override
    public Blog create(ModifiBlogDto dto) {
        Blog blog = new Blog();
        blog.setId(dto.getId());
        blog.setContent(dto.getContent());
        blog.setStatus(dto.getStatus());
        blog.setReaction(dto.getReaction());
        blog.setTitle(dto.getTitle());
        return blogRepository.save(blog);
    }
}

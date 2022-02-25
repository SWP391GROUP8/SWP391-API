package swp391.service.impl;

import org.springframework.stereotype.Service;
import swp391.dto.blog.ModifiBlogDto;

import swp391.dto.blog.UpdateBlogDto;
import swp391.entity.Blog;
import swp391.repository.BlogRepository;
import swp391.service.BlogService;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    @Override
    public boolean isExisted(Long id) {
        return blogRepository.existsById(id);
    }

    @Override
    public Blog create(ModifiBlogDto dto) {
        Blog blog = new Blog();

        blog.setContent(dto.getContent());
        blog.setStatus(dto.getStatus());
        blog.setReaction(dto.getReaction());
        blog.setTitle(dto.getTitle());
        return blogRepository.save(blog);
    }

    @Override
    public Blog update(Long id, UpdateBlogDto dto) {
        Blog blog = blogRepository.getById(dto.getId());
        blog.setContent(dto.getContent());
        blog.setStatus(dto.getStatus());
        blog.setReaction(dto.getReaction());
        blog.setTitle(dto.getTitle());
        return blogRepository.save(blog);
    }

    @Override
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Blog getById(Long id) {
        return blogRepository.getById(id);
    }

    @Override
    public int reaction(Long blogId) {
        Blog blog = blogRepository.getById(blogId);
        int count = blog.getReaction();
        count ++;
        blog.setReaction(count);
        blogRepository.save(blog);
        return blog.getReaction();
    }

    @Override
    public List<Blog> ranking() {
        return blogRepository.countMostBlogsByReaction();

    }
}

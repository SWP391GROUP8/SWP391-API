package swp391.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391.dto.user.CreateUserDto;
import swp391.dto.user.UpdateUserDto;
import swp391.entity.Role;
import swp391.entity.User;
import swp391.repository.BlogRepository;
import swp391.repository.RoleRepository;
import swp391.repository.UserRepository;
import swp391.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private BlogRepository blogRepository;
    private RoleRepository roleRepository;


    public UserServiceImpl(BlogRepository blogRepository,UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.blogRepository=blogRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User createUser(CreateUserDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setStatus("ACTIVE");
        Role role = roleRepository.findById(dto.getRoleId()).get();
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public boolean isExisted(String email) {
        return userRepository.existsById(email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(UpdateUserDto dto) {
        User user = userRepository.getById(dto.getEmail());
        user.setName(dto.getName());
        user.setBirthDay(dto.getBirthDay());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
       // user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setStatus(dto.getStatus());
        Role role = roleRepository.getById(dto.getRoleId());
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getById(email);
    }

    @Override
    public void deleteByEmail(String email) {
        userRepository.deleteById(email);
    }

    public Page<User> findUserWithPaging(int offset, int pageSize){
        Page<User> users = userRepository.findAll(PageRequest.of(offset,pageSize));
        return users;
    }

    @Override
    public boolean findByScheduleIdAndUserId(String scheduleId, String userId) {
        return userRepository.findUserByScheduleIdAndUserId(scheduleId,userId)>=1;
    }



    @Override
    public User findByEmail(String id) {
        return userRepository.getById(id);
    }

    @Override
    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword,user.getPassword()) ;
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void reaction(String email, Long blogId) {
        User user = userRepository.getById(email);
        user.addBlog(blogRepository.getById(blogId));
        userRepository.save(user);
    }

    @Override
    public Boolean isReaction(String email, Long blogId) {
        return userRepository.getReaction(email,blogId);
    }
}

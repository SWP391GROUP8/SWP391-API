package swp391.service;

import org.springframework.data.domain.Page;
import swp391.dto.user.CreateUserDto;
import swp391.dto.user.UpdateUserDto;
import swp391.entity.User;

import java.util.List;

public interface UserService {
    User createUser(CreateUserDto dto);

    boolean isExisted(String email);

    List<User> getAll();

    void updateUser(UpdateUserDto dto);

    User getByEmail(String email);

    void deleteByEmail(String email);

    Page<User> findUserWithPaging(int offset, int pageSize);

}

package swp391.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import swp391.dto.user.CreateUserDto;
import swp391.dto.user.PagingFormatUserDto;
import swp391.dto.user.UpdateUserDto;
import swp391.entity.User;
import swp391.service.RoleService;
import swp391.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    private RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping
    public Object createUser(@Valid @RequestBody CreateUserDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }
        if (!dto.getEmail().matches("^[\\w-\\.]+@fpt[\\.]edu[\\.]vn")) {
            return ResponseEntity.badRequest().body("Email flow format: ~~~~@fpt.edu.vn");
        }
        if (userService.isExisted(dto.getEmail())) {
            return ResponseEntity.badRequest().body("Email is duplicated");
        }
        if (!dto.getConfirmPassword().equals(dto.getPassword())) {
            return ResponseEntity.badRequest().body("Password and confirm password not match");
        }
//        if (!roleService.isExisted(dto.getRoleId())) {
//            return ResponseEntity.badRequest().body("Role Id not found");
//        }
        User user = userService.createUser(dto);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllUser() {

        List<User> userList = userService.getAll();
        return ResponseEntity.ok().body(userList);
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody UpdateUserDto dto) {
        if (!roleService.isExisted(dto.getRoleId())) {
            return ResponseEntity.badRequest().body("Role Id not found");
        }
        userService.updateUser(dto);
        return ResponseEntity.ok("Successful");
    }

    @GetMapping("/get-by-id/{email}")
    public ResponseEntity getById(@PathVariable String email) {
        User user = userService.getByEmail(email);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public PagingFormatUserDto<Page<User>> getUserWithPaging(@PathVariable int offset, @PathVariable int pageSize) {
        Page<User> productsWithPagination = userService.findUserWithPaging(offset, pageSize);
        return new PagingFormatUserDto<>(productsWithPagination.getSize(), productsWithPagination);

    }

    @DeleteMapping
    public ResponseEntity deleteUserByEmail(@RequestParam String email) {
        userService.deleteByEmail(email);
        return ResponseEntity.ok("Successful");
    }


}

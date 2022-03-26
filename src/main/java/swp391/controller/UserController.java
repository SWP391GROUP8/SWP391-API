package swp391.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import swp391.dto.user.ChangePasswordDto;
import swp391.dto.user.CreateUserDto;
import swp391.dto.user.PagingFormatUserDto;
import swp391.dto.user.UpdateUserDto;
import swp391.entity.Schedule;
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

    @PostMapping("/create-user")
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
        if (!roleService.isExisted(dto.getRoleId())) {
            return ResponseEntity.badRequest().body("Role Id not found");
        }
        User user = userService.createUser(dto);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllUser() {

        List<User> userList = userService.getAll();
        return ResponseEntity.ok().body(userList);
    }

    @PutMapping("/update-user")
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

    @PostMapping("/change-password")
    public ResponseEntity changPassword(ChangePasswordDto passwordDto) {
        // xac dinh user sau khi dang nhap
        //User user = userService.findByEmail(((User) SecurityContextHolder.getContext().getAuthentication().get).getEmail());
        User user = userService.findByEmail(passwordDto.getEmail());
        if (!userService.checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
            return ResponseEntity.badRequest().body("Old password is wrong");
        }
        if (!passwordDto.getNewPassword().equals(passwordDto.getComfirmNewPassword())) {
            return ResponseEntity.badRequest().body("New password not matches");
        }
        userService.changePassword(user, passwordDto.getNewPassword());
        return ResponseEntity.ok("Successful !");
    }

    @PutMapping("/reaction")
    private ResponseEntity reactionWW(@RequestParam String email, @RequestParam Long blogId, @RequestParam Boolean isReaction) {
        int reaction = userService.reaction(email, blogId, isReaction);
        return ResponseEntity.ok().body(reaction);
    }

    @GetMapping("/is-reaction")
    private ResponseEntity isReaction(@RequestParam String email, @RequestParam Long blogId) {
        Boolean reaction = userService.isReaction(email, blogId);
        return ResponseEntity.ok().body(reaction);
    }

    @GetMapping("/get-by-schedule-id/{scheduleId}")
    private Object getByScheduleId(@PathVariable String scheduleId) {
        List<User> userList = userService.getByScheduleId(scheduleId);
        return ResponseEntity.ok().body(userList);
    }
}

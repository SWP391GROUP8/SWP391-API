package swp391.sercurity.controller;

import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp391.entity.User;
import swp391.repository.RoleRepository;
import swp391.repository.UserRepository;
import swp391.sercurity.dto.LoginDto;
import swp391.sercurity.jwt.JwtUtils;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(Jwts.class);
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private RoleRepository roleRepository;
    private UserRepository userRepository;


    public AuthController(AuthenticationManager authManager, JwtUtils jwtUtils, PasswordEncoder encoder,RoleRepository roleRepository,UserRepository userRepository) {
        authenticationManager = authManager;
        this.jwtUtils = jwtUtils;
        this.roleRepository=roleRepository;
        this.userRepository=userRepository;

    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDto dto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }

//        Authentication auth = null;

        if (!dto.getEmail().matches("^[\\w-\\.]+@fpt[\\.]edu[\\.]vn")) {
            return ResponseEntity.badRequest().body("Email flow format: ~~~~@fpt.edu.vn");
        }
        if(!userRepository.existsById(dto.getEmail())){
            return ResponseEntity.badRequest().body("Email not exist");
        }
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwtUtils.generateJwtToken(auth);
            String role = roleRepository.getRoleByEmail(dto.getEmail());
            //return ResponseEntity.ok().body(token);
            return getResponse(token,role,HttpStatus.OK);
        } catch (Exception e) {
            logger.debug("{} has been logged in with wrong password: {}", dto.getEmail(), e.getMessage());
        }

        return ResponseEntity.badRequest().body("Invalid password");
    }

    public static ResponseEntity<Object> getResponse(Object token, Object role, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("content", token);
        map.put("role", role);
        map.put("status", status.value());

        return new ResponseEntity<Object>(map, status);
    }
}

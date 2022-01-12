package swp391.sercurity.controller;

import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import swp391.sercurity.dto.LoginDto;
import swp391.sercurity.jwt.JwtUtils;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(Jwts.class);
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    public AuthController(AuthenticationManager authManager, JwtUtils jwtUtils, PasswordEncoder encoder) {
        authenticationManager = authManager;
        this.jwtUtils = jwtUtils;

    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDto dto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }

//        Authentication auth = null;

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwtUtils.generateJwtToken(auth);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            logger.debug("{} has been logged in with wrong password: {}", dto.getEmail(), e.getMessage());
        }

        return ResponseEntity.badRequest().body("Invalid email or password");
    }
}

package swp391.sercurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import swp391.sercurity.jwt.JwtAuthorizationFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfig(UserDetailsService userDetailsService, JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        http.cors().configurationSource(request -> configuration.applyPermitDefaultValues());
       // http.cors();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();
//        login: /api/auth/login
//        View Profile user: /api/user/get-by-id/{email}
//        Edit Profile user: /api/user/update-user
//        Register: /api/user/create-user
//        View courses: /api/course/get-by-id ?????????????????????????????????????????????
//        Manage blogs: /api/blog/**
//         View course resources: /api/resource/get-by-id
//         Manage courses: /api/course/**
//         Manage user: /api/user/**
//         Create resources in course: /api/resource/create-resourse
//         Admin nhập lịch giảng viên: /api/schedule/add-user
//         Ranking of blog: /api/blog/ranking
//         Reply Q&A: /api/course-q&a/create-course-q&a
//         Manage Q&A: /api/course-q&a/**
//         Reaction: /api/user/reaction
//         Job registration: /api/job-posting/
//        http.authorizeRequests()
//                .antMatchers("/api/auth/login", "/swagger-ui.html#/**", "/api/user/get-by-id/{email}", "/api/user/update-user")
//                .permitAll()
//                //
//                .antMatchers("/api/resource/get-by-id", "/api/blog/**", "/api/course/**", "/api/user/**").
//                hasAnyAuthority("ROLE_ADMIN")//"/api/course-q&a/**",  "/api/file/**", "/api/job-posting/**", "/api/role/**", "/api/schedule/**"
//                //
//                .antMatchers("/api/user/create-user","/api/course/get-by-id", "/api/user/reaction", "/api/resource/get-by-id", "/api/blog/**", "/api/course-q&a/create-course-q&a","/api/resource/create-resourse").
//                hasAnyAuthority("ROLE_INSTRUCTORS")
//                //
//                .antMatchers("/api/course/get-by-id","/api/user/create-user", "/api/resource/get-by-id","/api/blog/**", "/api/course-q&a/**", "/api/blog/ranking").
//                hasAnyAuthority("ROLE_STUDENT")
//                //
//                // .antMatchers("").
//                // hasAnyAuthority("ROLE_COMPANY","ROLE_ADMIN")
//                .anyRequest().authenticated();

    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}

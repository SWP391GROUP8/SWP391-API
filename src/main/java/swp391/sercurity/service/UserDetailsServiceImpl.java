package swp391.sercurity.service;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import swp391.entity.Role;
import swp391.entity.User;
import swp391.repository.UserRepository;
import swp391.sercurity.dto.UserDetailsDto;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository repository;
    private final String status = "ACTIVE";

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = repository.findByEmailAndStatus(email,status);

        Set<GrantedAuthority> authorities = getAuthorities(user.getRole());
        return new UserDetailsDto(email, user.getPassword(), authorities);
    }

    private Set<GrantedAuthority> getAuthorities(Role role) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority(role.getName()));

        return authorities;
    }
}

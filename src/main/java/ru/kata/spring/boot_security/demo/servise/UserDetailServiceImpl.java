package ru.kata.spring.boot_security.demo.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByName(String name) {
        return userRepository.findByEmail(name);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), true, true, true,true, rolesToAuthority(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> rolesToAuthority(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole()))
                .collect(Collectors.toList());
    }
}

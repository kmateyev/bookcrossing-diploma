package kz.bookcrossing.service.impl;

import kz.bookcrossing.entity.User;
import kz.bookcrossing.entity.dto.UserDTO;
import kz.bookcrossing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                new ArrayList<>());
    }

    public User getUserByUsername(String username) {
        return userRepository.findByLogin(username);
    }

    public User save(UserDTO userDTO) {
        User userRepo = userRepository.findByLogin(userDTO.getLogin());
        if (userRepo == null) {
            User user = new User();
            user.setLogin(userDTO.getLogin());
//            user.setEmail(userDTO.getEmail());
//            if (userDTO.getIsAdmin() != null) {
//                user.setRoleAdmin(userDTO.getIsAdmin());
//            } else user.setRoleAdmin(false);
            user.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
            return userRepository.saveAndFlush(user);
        } else {
            return userRepo;
        }
    }
}

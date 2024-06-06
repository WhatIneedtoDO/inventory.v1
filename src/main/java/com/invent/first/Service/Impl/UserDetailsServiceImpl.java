package com.invent.first.Service.Impl;

import com.invent.first.DTO.DepartmentsDTO;
import com.invent.first.DTO.UserDTO;
import com.invent.first.Entity.Enum.Role;
import com.invent.first.Entity.User;
import com.invent.first.Repository.UserRepository;
import com.invent.first.Service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    public UserDetailsServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }
    @Override
    public User createUserFromLdap(String username, String password) {
        if (!repository.findByUsername(username).isPresent()) {
            User user = User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .role(Role.USER) // Default role
                    .build();
            return repository.save(user);
        }
        return repository.findByUsername(username).get();
    }


    @Override
    public User deleteById(Integer userId) {
        User user = repository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException("User not found"));
        repository.deleteById(userId);
        return user;
    }

    @Override
    public boolean changeRole(Integer id, Role newRole) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setRole(newRole);
            repository.save(user);

            return true;

        } else {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public boolean changeCurrentUserPassword(String currentPassword, String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        String username = authentication.getName();
        Optional<User> userOptional = repository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(currentPassword, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                repository.save(user);
                return true; // Пароль успешно изменен
            }
        }

        return false; // Пароль не изменен
    }

    @Override
    public void updatePassword(Integer userId, String newPassword) {
        Optional<User> userOptional = repository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            repository.save(user);
        } else {
            throw new EntityNotFoundException("User not found , check id");
        }
    }



    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = repository.findAll();
        return users.stream()
                .map(user -> UserDTO.builder()
                        .id(user.getId())
                        .firstname(user.getFirstname())
                        .lastname(user.getLastname())
                        .username(user.getUsername())
                        .department(DepartmentsDTO.fromDept(user.getDept()))
                        .role(user.getRole())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(Integer id) {
        Optional<User> user = repository.findById(id);
        return user.map(u -> UserDTO.builder()
                .id(u.getId())
                .firstname(u.getFirstname())
                .lastname(u.getLastname())
                .username(u.getUsername())
                .department(DepartmentsDTO.fromDept(u.getDept()))
                .role(u.getRole())
                .build());

    }
    public Optional<UserDTO> getUserByUsername(String username){
        Optional<User> user = repository.findByUsername(username);
        return user.map(u->UserDTO.builder()
                .id(u.getId())
                .firstname(u.getFirstname())
                .lastname(u.getLastname())
                .department(DepartmentsDTO.fromDept(u.getDept()))
                .role(u.getRole())
                .build());

    }




}



package com.alibou.security.Service.Impl;

import com.alibou.security.DTO.UserDTO;
import com.alibou.security.Entity.User;
import com.alibou.security.Repository.UserRepository;
import com.alibou.security.Service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserService {
    private UserRepository repository;
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }
    @Override
    public User saveUser(User user) {
        return repository.save(user);
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
                .role(u.getRole())
                .build());

    }
    public Optional<UserDTO> getUserByUsername(String username){
        Optional<User> user = repository.findByUsername(username);
        return user.map(u->UserDTO.builder()
                .id(u.getId())
                .firstname(u.getFirstname())
                .lastname(u.getLastname())
                .role(u.getRole())
                .build());

    }

}



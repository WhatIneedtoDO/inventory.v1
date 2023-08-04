package com.alibou.security.Service;

import com.alibou.security.DTO.UserDTO;
import com.alibou.security.Entity.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
 User saveUser(User user);
 List<UserDTO> getAllUsers();
 Optional<UserDTO> getUserById(Integer id);
 Optional<UserDTO>getUserByUsername(String username);

}

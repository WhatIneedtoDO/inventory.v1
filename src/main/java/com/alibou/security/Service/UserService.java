package com.alibou.security.Service;

import com.alibou.security.DTO.UserDTO;
import com.alibou.security.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
 User saveUser(User user);
 List<UserDTO> getAllUsers();

}

package com.invent.first.Service;

import com.invent.first.DTO.UserDTO;
import com.invent.first.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
 User saveUser(User user);
 List<UserDTO> getAllUsers();
 Optional<UserDTO> getUserById(Integer id);
 Optional<UserDTO> getUserByUsername(String username);
 User deleteById(Integer userId);

 void changeCurrentUserPassword(String currentPassword,String newPassword);
 void updatePassword(Integer userId, String newPassword);
}

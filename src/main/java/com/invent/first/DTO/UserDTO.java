package com.invent.first.DTO;

import com.invent.first.Entity.User;
import com.invent.first.Entity.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private Role role;

    public static UserDTO fromUser(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
    public static User toUser(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .username(userDTO.getUsername())
                .role(userDTO.getRole())
                .build();
    }
}

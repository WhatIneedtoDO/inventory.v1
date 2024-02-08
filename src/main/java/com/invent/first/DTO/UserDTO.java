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
    private DepartmentsDTO department;
    private Role role;

    public static UserDTO fromUser(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .department(DepartmentsDTO.fromDept(user.getDept()))
                .role(user.getRole())
                .build();
    }

}

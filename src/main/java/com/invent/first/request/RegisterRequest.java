package com.invent.first.request;

import com.invent.first.DTO.DepartmentsDTO;
import com.invent.first.Entity.Department;
import com.invent.first.Entity.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstname;
  private String lastname;
  private String username;
  private String password;
  private Role role;
  private Integer departmentId;
}

package com.invent.first.DTO;

import com.invent.first.Entity.City;
import com.invent.first.Entity.Department;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DepartmentsDTO {
    private Integer id;
    private String name;
    public static DepartmentsDTO fromDept(Department department) {
        return DepartmentsDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }

}

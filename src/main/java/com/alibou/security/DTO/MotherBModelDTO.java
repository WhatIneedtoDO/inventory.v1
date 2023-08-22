package com.alibou.security.DTO;

import com.alibou.security.Entity.City;
import com.alibou.security.Entity.MotherBModel;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MotherBModelDTO {
    private Integer id;
    private String name;

    public static MotherBModelDTO fromMotherBModel(MotherBModel motherBModel) {
        return MotherBModelDTO.builder()
                .id(motherBModel.getId())
                .name(motherBModel.getName())
                .build();
    }
}

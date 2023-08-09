package com.alibou.security.DTO;

import com.alibou.security.Entity.City;
import com.alibou.security.Entity.MotherBModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MotherBModelDTO {
    private Integer id;
    private String name;
    private List<ComputerDTO> computers;
    public static MotherBModelDTO fromMotherBModel(MotherBModel motherBModel) {
        return MotherBModelDTO.builder()
                .id(motherBModel.getId())
                .name(motherBModel.getName())
                .build();
    }
}

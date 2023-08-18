package com.alibou.security.DTO;


import com.alibou.security.Entity.CpuModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CpuModelDTO {
    private Integer id;
    private String name;

    public static CpuModelDTO fromCpuModel(CpuModel model) {
        return CpuModelDTO.builder()
                .id(model.getId())
                .name(model.getName())
                //.computers(computerDTOList)
                .build();
    }
}

package com.alibou.security.DTO;

import com.alibou.security.Entity.City;
import com.alibou.security.Entity.CpuModel;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CpuModelDTO {
    private Integer id;
    private String name;
    public static CpuModelDTO fromCpuModel(CpuModel model) {


        return CpuModelDTO.builder()
                .id(model.getId())
                .name(model.getName())
                .build();
    }
}

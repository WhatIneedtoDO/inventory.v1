package com.invent.first.DTO;

import com.invent.first.Entity.CpuModel;
import lombok.*;

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

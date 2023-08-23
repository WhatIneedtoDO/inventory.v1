package com.invent.first.DTO;

import com.invent.first.Entity.CpuProduction;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CpuProductionDTO {
    private Integer id;
    private String name;
    private List<ComputerDTO> computers;
    public static CpuProductionDTO fromCpuProduction(CpuProduction production) {

        return CpuProductionDTO.builder()
                .id(production.getId())
                .name(production.getName())
                .build();
    }
}

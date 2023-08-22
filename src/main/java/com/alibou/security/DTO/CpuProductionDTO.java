package com.alibou.security.DTO;

import com.alibou.security.Entity.City;
import com.alibou.security.Entity.CpuProduction;
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

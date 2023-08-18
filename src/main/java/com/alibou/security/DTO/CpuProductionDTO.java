package com.alibou.security.DTO;


import com.alibou.security.Entity.CpuProduction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CpuProductionDTO {
    private Integer id;
    private String name;

    public static CpuProductionDTO fromCpuProduction(CpuProduction production) {

        return CpuProductionDTO.builder()
                .id(production.getId())
                .name(production.getName())

                .build();
    }
}

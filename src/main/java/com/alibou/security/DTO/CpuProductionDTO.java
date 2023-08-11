package com.alibou.security.DTO;

import com.alibou.security.Entity.City;
import com.alibou.security.Entity.CpuProduction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CpuProductionDTO {
    private Integer id;
    private String name;
    private List<ComputerDTO> computers;
    public static CpuProductionDTO fromCpuProduction(CpuProduction production) {
//        List<ComputerDTO> computerDTOList = city.getComputers().stream()
//                .map(ComputerDTO::fromComputer)
//                .collect(Collectors.toList());

        return CpuProductionDTO.builder()
                .id(production.getId())
                .name(production.getName())
                //.computers(computerDTOList)
                .build();
    }
}

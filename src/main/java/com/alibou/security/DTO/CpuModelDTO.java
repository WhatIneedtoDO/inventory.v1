package com.alibou.security.DTO;

import com.alibou.security.Entity.City;
import com.alibou.security.Entity.CpuModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CpuModelDTO {
    private Integer id;
    private String name;
    private List<ComputerDTO> computers;
    public static CpuModelDTO fromCpuModel(CpuModel model) {
//        List<ComputerDTO> computerDTOList = city.getComputers().stream()
//                .map(ComputerDTO::fromComputer)
//                .collect(Collectors.toList());

        return CpuModelDTO.builder()
                .id(model.getId())
                .name(model.getName())
                //.computers(computerDTOList)
                .build();
    }
}

package com.alibou.security.DTO;

import com.alibou.security.Entity.MotherBProd;
import com.alibou.security.Entity.Productions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductionsDTO {
    private Integer id;
    private String name;
    private List<ComputerDTO> computers;
    public static ProductionsDTO fromProductions(Productions production) {
//        List<ComputerDTO> computerDTOList = prod.getComputers().stream()
//                .map(ComputerDTO::fromComputer)
//                .collect(Collectors.toList());

        return ProductionsDTO.builder()
                .id(production.getId())
                .name(production.getName())
   //             .computers(computerDTOList)
                .build();
    }
}

package com.alibou.security.DTO;

import com.alibou.security.Entity.Enum.Role;
import com.alibou.security.Entity.Productions;
import com.alibou.security.Entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductionsDTO {
    private Integer id;
    private String name;
    private List<ComputerDTO> computers;
    public static ProductionsDTO fromProductions(Productions prod) {
//        List<ComputerDTO> computerDTOList = prod.getComputers().stream()
//                .map(ComputerDTO::fromComputer)
//                .collect(Collectors.toList());

        return ProductionsDTO.builder()
                .id(prod.getId())
                .name(prod.getName())
   //             .computers(computerDTOList)
                .build();
    }
}

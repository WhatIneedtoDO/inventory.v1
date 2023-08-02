package com.alibou.security.DTO;

import com.alibou.security.Entity.Model;
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
public class ModelDTO {
    private Integer id;
    private String name;
    private List<ComputerDTO> computers;

    public static ModelDTO fromModel(Model model){
//        List<ComputerDTO> computerDTOList = model.getComputers().stream()
//                .map(ComputerDTO::fromComputer)
//                .collect(Collectors.toList());
        return ModelDTO.builder()
                .id(model.getId())
                .name(model.getName())
  //              .computers(computerDTOList)
                .build();
    }
}

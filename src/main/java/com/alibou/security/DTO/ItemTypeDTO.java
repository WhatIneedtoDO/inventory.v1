package com.alibou.security.DTO;


import com.alibou.security.Entity.ItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemTypeDTO {
    private Integer id;
    private String name;
    private List<ComputerDTO> computers;
    public static ItemTypeDTO fromItemType(ItemType itemType) {
//        List<ComputerDTO> computerDTOList = itemType.getComputers().stream()
//                .map(ComputerDTO::fromComputer)
//                .collect(Collectors.toList());
        return ItemTypeDTO.builder()
                .id(itemType.getId())
                .name(itemType.getName())
       //         .computers(computerDTOList)
                .build();
    }
}

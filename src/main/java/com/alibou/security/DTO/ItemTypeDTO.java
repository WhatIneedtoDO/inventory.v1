package com.alibou.security.DTO;


import com.alibou.security.Entity.ItemType;
import lombok.*;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ItemTypeDTO {
    private Integer id;
    private String name;
    public static ItemTypeDTO fromItemType(ItemType itemType) {
        return ItemTypeDTO.builder()
                .id(itemType.getId())
                .name(itemType.getName())
                .build();
    }
}

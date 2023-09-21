package com.invent.first.DTO;


import com.invent.first.Entity.ItemType;
import lombok.*;


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
                .name(itemType.getTypename())
                .build();
    }
}

package com.invent.first.DTO;

import com.invent.first.Entity.MotherBModel;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MotherBModelDTO {
    private Integer id;
    private String name;

    public static MotherBModelDTO fromMotherBModel(MotherBModel motherBModel) {
        return MotherBModelDTO.builder()
                .id(motherBModel.getId())
                .name(motherBModel.getName())
                .build();
    }
}

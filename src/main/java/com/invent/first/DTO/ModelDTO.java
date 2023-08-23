package com.invent.first.DTO;

import com.invent.first.Entity.Model;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ModelDTO {
    private Integer id;
    private String name;

    public static ModelDTO fromModel(Model model){
        return ModelDTO.builder()
                .id(model.getId())
                .name(model.getName())
                .build();
    }
}

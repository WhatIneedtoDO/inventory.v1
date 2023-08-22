package com.alibou.security.DTO;

import com.alibou.security.Entity.Model;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

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

package com.invent.first.DTO;

import com.invent.first.Entity.Productions;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductionsDTO {
    private Integer id;
    private String name;

    public static ProductionsDTO fromProductions(Productions production) {
        return ProductionsDTO.builder()
                .id(production.getId())
                .name(production.getName())
                .build();
    }
}

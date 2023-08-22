package com.alibou.security.DTO;

import com.alibou.security.Entity.MotherBProd;
import com.alibou.security.Entity.Productions;
import lombok.*;

import java.util.List;

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

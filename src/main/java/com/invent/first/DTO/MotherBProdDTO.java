package com.invent.first.DTO;

import com.invent.first.Entity.MotherBProd;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MotherBProdDTO {
    private Integer id;
    private String name;
    public static MotherBProdDTO fromMotherBProd(MotherBProd motherBProd) {
        return MotherBProdDTO.builder()
                .id(motherBProd.getId())
                .name(motherBProd.getName())
                .build();
    }
}

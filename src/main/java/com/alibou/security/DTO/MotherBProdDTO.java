package com.alibou.security.DTO;

import com.alibou.security.Entity.MotherBModel;
import com.alibou.security.Entity.MotherBProd;
import lombok.*;

import java.util.List;

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

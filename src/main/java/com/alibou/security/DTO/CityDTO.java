package com.alibou.security.DTO;

import com.alibou.security.Entity.City;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CityDTO {
    private Integer id;
    private String name;
    public static CityDTO fromCity(City city) {
        return CityDTO.builder()
                .id(city.getId())
                .name(city.getName())
                .build();
    }
}

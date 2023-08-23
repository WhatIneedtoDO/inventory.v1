package com.invent.first.DTO;

import com.invent.first.Entity.City;
import lombok.*;

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

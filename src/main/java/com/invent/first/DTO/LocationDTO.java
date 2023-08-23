package com.invent.first.DTO;

import com.invent.first.Entity.Location;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LocationDTO {
    private Integer id;
    private String street;
    private String number;
    private Integer ekp;

    public static LocationDTO fromLocation(Location location){

        return LocationDTO.builder()
                .id(location.getId())
                .street(location.getStreet())
                .number(location.getNumber())
                .ekp(location.getEkp())
                .build();
    }

}

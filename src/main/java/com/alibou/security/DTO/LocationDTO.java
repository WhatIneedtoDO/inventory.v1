package com.alibou.security.DTO;

import com.alibou.security.Entity.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {
    private Integer id;
    private String street;
    private String number;
    private Integer ekp;
    private List<ComputerDTO> computers;

    public static LocationDTO fromLocation(Location location){
//        List<ComputerDTO> computerDTOList = location.getComputers().stream()
//                .map(ComputerDTO::fromComputer)
//                .collect(Collectors.toList());
        return LocationDTO.builder()
                .id(location.getId())
                .street(location.getStreet())
                .number(location.getNumber())
                .ekp(location.getEkp())
   //             .computers(computerDTOList)
                .build();
    }

}

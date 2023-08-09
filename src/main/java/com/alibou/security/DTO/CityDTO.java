package com.alibou.security.DTO;

import com.alibou.security.Entity.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {
    private Integer id;
    private String name;
    private List<ComputerDTO> computers;
    public static CityDTO fromCity(City city) {
//        List<ComputerDTO> computerDTOList = city.getComputers().stream()
//                .map(ComputerDTO::fromComputer)
//                .collect(Collectors.toList());

        return CityDTO.builder()
                .id(city.getId())
                .name(city.getName())
   //             .computers(computerDTOList)
                .build();
    }
}

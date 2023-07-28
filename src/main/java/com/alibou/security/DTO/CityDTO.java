package com.alibou.security.DTO;

import com.alibou.security.Entity.City;
import com.alibou.security.Entity.Productions;
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

package com.alibou.security.DTO.OutDTO;

import com.alibou.security.DTO.*;
import com.alibou.security.Entity.Enum.RAM;
import com.alibou.security.Entity.Enum.SSD;
import com.alibou.security.Entity.Enum.Serviceability;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonitorOutDTO {
    private Integer id;
    private Integer i_card;
    private String serialnumber;
    private String i_number;
    private ProductionsDTO production;
    private ModelDTO model;
    private ItemTypeDTO itemType;
    private Integer size;
    private Integer year;
    private Serviceability serv;
    private CityDTO city;
    private LocationDTO location;
    private Integer room;
    private UserDTO userId;
    private Date staydate;
    private Double price;
    private String comment;
}

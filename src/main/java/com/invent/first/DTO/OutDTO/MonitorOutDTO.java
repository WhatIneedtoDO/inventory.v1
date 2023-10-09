package com.invent.first.DTO.OutDTO;


import com.invent.first.DTO.*;
import com.invent.first.Entity.Enum.Serviceability;
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
    //порты
    private Integer hdmi;
    private Integer vga;
    private Integer displayport;
    private Integer dvid;
    //
    private Integer year;
    private Serviceability serv;
    private CityDTO city;
    private LocationDTO location;
    private Integer room;
    private UserDTO userId;
    private Date staydate;
    private Double price;
    private String comment;
    private Boolean spisano;
}

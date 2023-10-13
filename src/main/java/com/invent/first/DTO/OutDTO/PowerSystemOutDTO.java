package com.invent.first.DTO.OutDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.invent.first.DTO.*;
import com.invent.first.Entity.Enum.Serviceability;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PowerSystemOutDTO {
    private Integer id;
    private Integer i_card;
    private String serialnumber;
    private String i_number;
    private ProductionsDTO production;
    private ModelDTO model;
    private ItemTypeDTO itemType;
    private String ip;
    private Boolean network;
    private Integer year;
    private Serviceability serv;
    private CityDTO city;
    private LocationDTO location;
    private Integer room;
    private Integer closet;
    @JsonIgnoreProperties(value = {"password", "role","token"})
    private UserDTO userId;
    private Date staydate;
    private Double price;
    private String comment;
    private Boolean spisano;
}

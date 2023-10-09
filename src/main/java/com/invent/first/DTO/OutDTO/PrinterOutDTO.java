package com.invent.first.DTO.OutDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.invent.first.DTO.*;
import com.invent.first.Entity.Enum.PrinterEnum.*;
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
public class PrinterOutDTO {
    private Integer id;
    private Integer i_card;
    private String serialnumber;
    private String i_number;
    private ProductionsDTO production;
    private ModelDTO model;
    private ItemTypeDTO itemType;
    //
    private String name;
    private String ip;
    private PRINTERTYPE type;
    private PRINTFORMAT format;
    private PRINTERCLASS printerClass;
    private PRINTERCOLOR color;
    private TYPEPECHAT typePechat;
    private Boolean secondSide;
    //
    private Integer year;
    private Serviceability serv;
    private CityDTO city;
    private LocationDTO location;
    private Integer room;
    @JsonIgnoreProperties(value = {"password", "role","token"})
    private UserDTO userId;
    private Date staydate;
    private Double price;
    private String comment;
    private Boolean spisano;

}

package com.invent.first.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.invent.first.Entity.Enum.PrinterEnum.*;
import com.invent.first.Entity.Enum.Serviceability;
import lombok.*;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PrinterDTO {

    private Integer id;
    private Integer i_card;
    private String serialnumber;
    private String i_number;
    private Integer production;
    private Integer model;
    private Integer itemType;
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
    private Integer city;
    private Integer location;
    private Integer room;
    @JsonIgnoreProperties(value = {"password", "role","token"})
    private Integer userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date staydate;
    private Double price;
    private String comment;
    private Boolean spisano;
}

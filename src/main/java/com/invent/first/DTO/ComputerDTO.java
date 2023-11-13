package com.invent.first.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.invent.first.Entity.*;
import com.invent.first.Entity.Enum.HDD;
import com.invent.first.Entity.Enum.RAM;
import com.invent.first.Entity.Enum.SSD;
import com.invent.first.Entity.Enum.Serviceability;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ComputerDTO {

    //ComputerDTO to Entity
    private Integer id;
    private Integer i_card;
    private String serialnumber;
    private String i_number;
    private Integer production;
    private Integer model;
    private Integer itemType;
    private SSD ssd;
    private HDD hdd;
    private Integer motherBProd;
    private Integer motherBModel;
    private Integer slotsvalue;
    private Integer slotsuse;
    private Integer ramtype;
    private RAM ram;
    private Integer bp;
    private Integer cpuproduction;
    private Integer cpumodel;
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

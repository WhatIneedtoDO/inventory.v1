package com.alibou.security.DTO;

import com.alibou.security.Entity.*;
import com.alibou.security.Entity.Enum.HDD;
import com.alibou.security.Entity.Enum.RAM;
import com.alibou.security.Entity.Enum.SSD;
import com.alibou.security.Entity.Enum.Serviceability;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Date staydate;
    private Double price;
    private String comment;

}

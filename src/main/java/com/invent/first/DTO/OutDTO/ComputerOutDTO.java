package com.invent.first.DTO.OutDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ComputerOutDTO {
    private Integer id;
    private Integer i_card;
    private String serialnumber;
    private String i_number;
    private ProductionsDTO production;
    private ModelDTO model;
    private ItemTypeDTO itemType;
    private Integer ssd;
    private Integer hdd;
    private MotherBProdDTO motherBProd;
    private MotherBModelDTO motherBModel;
    private Integer slotsvalue;
    private Integer slotsuse;
    private Integer ramtype;
    private Integer ram;
    private Integer bp;
    private CpuProductionDTO cpuproduction;
    private CpuModelDTO cpumodel;
    private Integer year;
    private Serviceability serv;
    private CityDTO city;
    private LocationDTO location;
    private Integer room;
    private UserDTO userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date staydate;
    private Double price;
    private String comment;
    private Boolean spisano;

}
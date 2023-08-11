package com.alibou.security.DTO.OutDTO;

import com.alibou.security.DTO.*;
import com.alibou.security.Entity.CpuModel;
import com.alibou.security.Entity.CpuProduction;
import com.alibou.security.Entity.Enum.Serviceability;
import com.alibou.security.Entity.MotherBModel;
import com.alibou.security.Entity.MotherBProd;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
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
    private Date staydate;
    private Double price;
    private String comment;

}
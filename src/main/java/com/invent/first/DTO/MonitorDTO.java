package com.invent.first.DTO;

import com.invent.first.Entity.Enum.Serviceability;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MonitorDTO {
    private Integer id;
    private Integer i_card;
    private String serialnumber;
    private String i_number;
    private Integer production;
    private Integer model;
    private Integer itemType;
    private Integer size;
    //порты
    private Integer hdmi;
    private Integer vga;
    private Integer displayport;
    private Integer dvid;
    //
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

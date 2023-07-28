package com.alibou.security.DTO;

import com.alibou.security.Entity.*;
import com.alibou.security.Entity.Enum.RAM;
import com.alibou.security.Entity.Enum.SSD;
import com.alibou.security.Entity.Enum.Serviceability;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComputerDTO {

    //Computer
    private Integer id;
    private Integer i_card;
    private String serialnumber;
    private String i_number;
    private Integer production;
    private Integer model;
    private Integer itemType;
    private SSD ssd;
    private RAM ram;
    private Integer bp;
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

    public static ComputerDTO fromComputer(Computer computer) {
        return ComputerDTO.builder()
                .id(computer.getId())
                .i_card(computer.getI_card())
                .serialnumber(computer.getSerialnumber())
                .i_number(computer.getI_number())
                .production(computer.getProduction().getId())//id
                .model(computer.getModel().getId())//id
                .itemType(computer.getItemType().getId())//id
                .ssd(computer.getSsd())
                .ram(computer.getRam())
                .bp(computer.getBp())
                .year(computer.getYear())
                .serv(computer.getServ())
                .city(computer.getCity().getId())//id
                .location(computer.getLocation().getId())//id
                .room(computer.getRoom())
                .userId(computer.getUser().getId())//id
                .staydate(computer.getStaydate())
                .price(computer.getPrice())
                .comment(computer.getComment())
                .build();
    }
}

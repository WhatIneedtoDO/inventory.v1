package com.alibou.security.DTO.OutDTO;

import com.alibou.security.DTO.*;
import com.alibou.security.Entity.Enum.Serviceability;
import com.alibou.security.Entity.MotherBModel;
import com.alibou.security.Entity.MotherBProd;
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
    private String cpu;
    private Integer year;
    private Serviceability serv;
    private CityDTO city;
    private LocationDTO location;
    private Integer room;
    private UserDTO userId;

    private Date staydate;
    private Double price;
    private String comment;

    //после тестов удалить т.к. уже есть в компутер сервис
    /*  public static ComputerOutDTO fromComputerOut(Computer computer) {
        UserDTO userDTO = UserDTO.fromUser(computer.getUser());
        ProductionsDTO productionsDTO = ProductionsDTO.fromProductions(computer.getProduction());
        ModelDTO modelDTO = ModelDTO.fromModel(computer.getModel());
        ItemTypeDTO itemTypeDTO = ItemTypeDTO.fromItemType(computer.getItemType());
        CityDTO cityDTO = CityDTO.fromCity(computer.getCity());
        LocationDTO locationDTO = LocationDTO.fromLocation(computer.getLocation());
        return ComputerOutDTO
                .builder()
                .id(computer.getId())
                .i_card(computer.getI_card())
                .serialnumber(computer.getSerialnumber())
                .i_number(computer.getI_number())
                .production(productionsDTO)
                .model(modelDTO)
                .itemType(itemTypeDTO)
                .ssd(computer.getSsd())
                .ram(computer.getRam())
                .bp(computer.getBp())
                .year(computer.getYear())
                .serv(computer.getServ())
                .city(cityDTO)
                .location(locationDTO)
                .room(computer.getRoom())
                .userId(userDTO)
                .staydate(computer.getStaydate())
                .price(computer.getPrice())
                .comment(computer.getComment())
                .build();
    }*/
}
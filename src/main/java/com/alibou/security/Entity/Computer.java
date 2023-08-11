package com.alibou.security.Entity;

import com.alibou.security.Entity.Enum.HDD;
import com.alibou.security.Entity.Enum.RAM;
import com.alibou.security.Entity.Enum.SSD;
import com.alibou.security.Entity.Enum.Serviceability;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "computers")
public class Computer {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer i_card;

    private String serialnumber;

    private String i_number;
    @JsonIgnoreProperties({"computers", "monitors"})
    @ManyToOne
    @JoinColumn(name = "productions_id")
    private Productions production;
    @JsonIgnoreProperties({"computers", "monitors"})
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
    @JsonIgnoreProperties({"computers", "monitors"})
    @ManyToOne
    @JoinColumn(name = "item_type_id")
    private ItemType itemType;
    @Enumerated(EnumType.STRING)
    private SSD ssd;
    @Enumerated(EnumType.STRING)
    private HDD hdd;
    @JsonIgnoreProperties({"computers"})
    @ManyToOne
    @JoinColumn(name = "motherbprod_id")
    private MotherBProd motherbprod;
    @JsonIgnoreProperties({"computers"})
    @ManyToOne
    @JoinColumn(name = "motherbmodel_id")
    private MotherBModel motherbmodel;
    private Integer slotsvalue;
    private Integer slotsuse;
    private Integer ramtype;
    @Enumerated(EnumType.STRING)
    private RAM ram;
    private Integer bp;
    @JsonIgnoreProperties({"computers"})
    @ManyToOne
    @JoinColumn(name = "cpuproduction_id")
    private CpuProduction cpuproduction;
    @JsonIgnoreProperties({"computers"})
    @ManyToOne
    @JoinColumn(name = "cpumodel_id")
    private CpuModel cpumodel;
    private Integer year;
    @Enumerated(EnumType.STRING)
    private Serviceability serv;
    @JsonIgnoreProperties({"computers", "monitors"})
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @JsonIgnoreProperties({"computers", "monitors"})
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    private Integer room;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"password", "role", "token"})
    private User user;
    private Date staydate;
    private Double price;
    private String comment;


    protected boolean canEqual(final Object other) {
        return other instanceof Computer;
    }

}

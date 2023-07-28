package com.alibou.security.Entity;

import com.alibou.security.Entity.Enum.RAM;
import com.alibou.security.Entity.Enum.Role;
import com.alibou.security.Entity.Enum.SSD;
import com.alibou.security.Entity.Enum.Serviceability;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @ManyToOne
    @JoinColumn(name ="productions_id")
    @JsonIgnoreProperties({"computers"})
    private Productions production;
    @ManyToOne
    @JoinColumn(name ="model_id")
    @JsonIgnoreProperties({"computers"})
    private Model model;
    @ManyToOne
    @JoinColumn(name = "item_type_id")
    @JsonIgnoreProperties({"computers"})
    private ItemType itemType;
    @Enumerated(EnumType.STRING)
    private SSD ssd;
    @Enumerated(EnumType.STRING)
    private RAM ram;
    private Integer bp;
    private Integer year;
    @Enumerated(EnumType.STRING)
    private Serviceability serv;
    @ManyToOne
    @JoinColumn(name ="city_id")
    @JsonIgnoreProperties({"computers"})
    private City city;
    @ManyToOne
    @JoinColumn(name ="location_id")
    @JsonIgnoreProperties({"computers"})
    private Location location;
    private Integer room;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnoreProperties({"password", "role","token"})
    private User user;
    private Date staydate;
    private Double price;
    private String comment;



}

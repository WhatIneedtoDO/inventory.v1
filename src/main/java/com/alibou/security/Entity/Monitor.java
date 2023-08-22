package com.alibou.security.Entity;

import com.alibou.security.Entity.Enum.Serviceability;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "monitors")
public class Monitor {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer i_card;

    private String serialnumber;

    private String i_number;
    @ManyToOne
    @JoinColumn(name ="productions_id")
    @JsonIgnoreProperties({"computers","monitors"})
    private Productions production;
    @ManyToOne
    @JoinColumn(name ="model_id")
    @JsonIgnoreProperties({"computers","monitors"})
    private Model model;
    @ManyToOne
    @JoinColumn(name = "item_type_id")
    @JsonIgnoreProperties({"computers","monitors"})
    private ItemType itemType;
    private Integer size;
    //порты
    private Integer hdmi;
    private Integer vga;
    private Integer displayport;
    private Integer dvid;
    //
    private Integer year;
    @Enumerated(EnumType.STRING)
    private Serviceability serv;
    @ManyToOne
    @JoinColumn(name ="city_id")
    @JsonIgnoreProperties({"computers","monitors"})
    private City city;
    @ManyToOne
    @JoinColumn(name ="location_id")
    @JsonIgnoreProperties({"computers","monitors"})
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

package com.invent.first.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.invent.first.Entity.Enum.Serviceability;
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
@Table(name = "servereqs")
public class ServerEqs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer i_card;
    @Column(unique = true)
    private String serialnumber;
    @Column(unique = true)
    private String i_number;
    @ManyToOne
    @JoinColumn(name = "productions_id")
    private Productions production;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
    @ManyToOne
    @JoinColumn(name = "item_type_id")
    private ItemType itemType;
    //------уникальные столбцы
    private String name;
    private String ip;
    //-----
    private Integer year;
    @Enumerated(EnumType.STRING)
    private Serviceability serv;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    private Integer room;
    private Integer closet;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"password", "role", "token"})
    private User user;
    private Date staydate;
    private Double price;
    private String comment;
    private Boolean spisano;
}

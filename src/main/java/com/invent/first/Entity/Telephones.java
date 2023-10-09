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
@Table(name = "telephones")
public class Telephones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String mac;
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
    private Boolean spisano;
}

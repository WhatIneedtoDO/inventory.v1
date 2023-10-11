package com.invent.first.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String number;
    private Integer ekp;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Computer> computers;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Monitor> monitors;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Printers> printers;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Telephones> telephones;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<ServerEqs> serverEqs;

}

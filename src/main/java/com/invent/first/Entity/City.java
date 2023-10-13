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
@Table(name = "city")
public class City {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Computer> computers;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Monitor> monitors;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Printers> printers;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Telephones> telephones;
    @OneToMany(mappedBy = "city",cascade = CascadeType.ALL)
    private List<ServerEqs> serverEqs;
    @OneToMany(mappedBy = "city",cascade = CascadeType.ALL)
    private List<PowerSystem> powerSystem;



}

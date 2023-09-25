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
@Table(name = "pmodel")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany( mappedBy= "model",cascade = CascadeType.ALL)
    private List <Computer> computers;
    @OneToMany( mappedBy= "model",cascade = CascadeType.ALL)
    private List <Monitor> monitors;
    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    private List<Printers> printers;
    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    private List<Telephones> telephones;
}

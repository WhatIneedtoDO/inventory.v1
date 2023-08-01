package com.alibou.security.Entity;

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
    @GeneratedValue
    private Integer id;
    private String street;
    private String number;
    private Integer ekp;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Computer> computers;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Monitor> monitors;

}

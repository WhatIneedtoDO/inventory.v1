package com.alibou.security.Entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
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
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Computer> computers;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Monitor> monitors;


}

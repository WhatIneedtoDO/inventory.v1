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
@Table(name = "pmodel")
public class Model {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany( mappedBy= "model",cascade = CascadeType.ALL)
    private List <Computer> computers;
    @OneToMany( mappedBy= "model",cascade = CascadeType.ALL)
    private List <Monitor> monitors;
}

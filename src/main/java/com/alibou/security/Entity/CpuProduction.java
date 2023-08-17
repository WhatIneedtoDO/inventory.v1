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
@Table(name = "cpuproduction")
public class CpuProduction {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany( mappedBy= "cpuproduction",cascade = CascadeType.ALL)
    private List<Computer> computers;
}
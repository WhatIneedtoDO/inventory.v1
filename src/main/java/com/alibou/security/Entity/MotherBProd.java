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
@Table(name = "motherbprod")
public class MotherBProd {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany( mappedBy= "motherbprod",cascade = CascadeType.ALL)
    private List<Computer> computers;


}

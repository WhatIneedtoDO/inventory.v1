package com.alibou.security.Entity;

import com.alibou.security.Entity.Enum.RAM;
import com.alibou.security.Entity.Enum.Role;
import com.alibou.security.Entity.Enum.SSD;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "computers")
public class Computer {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer i_card;

    private String serialnumber;

    private String i_number;
    @ManyToOne
    @JoinColumn(name ="productions_id")
    private Productions production;
    @ManyToOne
    @JoinColumn(name ="model_id")
    private Model model;
    @ManyToOne
    @JoinColumn(name = "item_type_id")
    private ItemType itemType;
    @Enumerated(EnumType.STRING)
    private SSD ssd;
    @Enumerated(EnumType.STRING)
    private RAM ram;




}

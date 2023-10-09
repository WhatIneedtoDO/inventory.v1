package com.invent.first.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "trash")
public class Trash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "equipment_id")
    private Integer equipment_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_type_id")
    @JsonIgnoreProperties({"computers", "monitors"})
    private ItemType itemType;
    private Date trashDate;
}

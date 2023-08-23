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
@Table(name = "item_type")
public class ItemType {

        @Id
        @GeneratedValue
        private Integer id;
        private String name;
        @OneToMany(mappedBy = "itemType", cascade = CascadeType.ALL)
        private List<Computer> computers;
        @OneToMany(mappedBy = "itemType", cascade = CascadeType.ALL)
        private List<Monitor> monitors;
        @OneToMany(mappedBy = "itemType",cascade = CascadeType.ALL)
        private List<HistoryOfChanges> history;

}

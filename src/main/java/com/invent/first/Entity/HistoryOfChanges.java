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
@Table(name = "history_of_change")
public class HistoryOfChanges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"password", "role", "token"})
    private User user;
    @Column(name = "equipment_id")
    private Integer equipmentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_type_id")
    @JsonIgnoreProperties({"computers", "monitors"})
    private ItemType itemType;
    private Date changeDate;
    @Column(columnDefinition = "TEXT")
    private String changeDetails;
}

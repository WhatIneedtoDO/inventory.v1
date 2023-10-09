package com.invent.first.DTO;

import lombok.*;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TrashDTO {
    private Integer id;
    private Integer equipmentId;
    private Integer itemtypeId;
    private Date trashDate;
}

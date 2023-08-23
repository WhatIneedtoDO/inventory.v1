package com.invent.first.DTO;

import lombok.*;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class HistoryDTO {
    private Integer id;
    private Integer user;
    private Integer equipmentId;// сделать getInventorynumber по id
    private Integer itemtypeId;
    private Date changeDate;
    private String changedetails;

}

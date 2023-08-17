package com.alibou.security.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDTO {
    private Integer id;
    private Integer user;
    private Integer equipmentId;// сделать getInventorynumber по id
    private Integer itemtypeId;
    private Date changeDate;
    private String changedetails;

}

package com.alibou.security.DTO.OutDTO;

import com.alibou.security.DTO.ItemTypeDTO;
import com.alibou.security.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryOutDTO {
    private Integer id;
    private UserDTO user;
    private Integer equipmentId;
    private ItemTypeDTO itemType;
    private Date changedate;
    private String details;
}

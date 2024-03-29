package com.invent.first.DTO.OutDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.invent.first.DTO.ItemTypeDTO;
import com.invent.first.DTO.UserDTO;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date changedate;
    private String details;
}

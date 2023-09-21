package com.invent.first.DTO.OutDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkstationOutDTO {
    private Integer id;
    private String i_number;
    private String typename;
    private Integer i_card;
    private String serialNumber;
    private Integer room;
    private String name;
    private String ekp;
    private String number;
    private String street;
    private String username;
    private String firstname;
    private String lastname;
}

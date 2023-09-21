package com.invent.first.Repository;

import com.invent.first.DTO.OutDTO.WorkstationOutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class WorkStationRepositoryJDBC {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkStationRepositoryJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<WorkstationOutDTO> findAllWorkstation() {
        String sql = "SELECT c.id,c.i_number,t.typename,c.i_card,c.serialnumber,c.room,ct.name,l.ekp, l.number, l.street,u.username, u.firstname, u.lastname  FROM computers c " +
                "inner join invent._user u on c.user_id = u.id " +
                "inner join invent.item_type t on c.item_type_id = t.id " +
                "inner join invent.location l on c.location_id = l.id " +
                "inner join invent.city ct on c.city_id = ct.id " +
                "INNER JOIN invent.monitors m ON c.i_number = m.i_number " +
                "union all " +
                "SELECT m.id,m.i_number,t.typename,m.i_card,m.serialnumber,m.room,ct.name,l.ekp, l.number, l.street,u.username, u.firstname, u.lastname FROM monitors m " +
                "inner join invent._user u on m.user_id = u.id " +
                "inner join invent.city ct on m.city_id = ct.id " +
                "inner join invent.item_type t on m.item_type_id = t.id " +
                "inner join invent.location l on m.location_id = l.id " +
                "inner JOIN invent.computers c ON c.i_number = m.i_number " +
                "where c.i_number = m.i_number " +
                "order by i_number asc ";
        List<WorkstationOutDTO> workstations;
        workstations = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WorkstationOutDTO.class));
        return workstations;
        }
    }


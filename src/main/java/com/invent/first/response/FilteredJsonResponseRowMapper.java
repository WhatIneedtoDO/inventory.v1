package com.invent.first.response;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilteredJsonResponseRowMapper implements RowMapper<FilteredJsonResponse> {
    @Override
    public FilteredJsonResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FilteredJsonResponse.builder()
                .id(rs.getInt("id"))
                .item_type(rs.getString("item_type"))
                .i_number(rs.getString("i_number"))
                .i_card(rs.getInt("i_card"))
                .serialnumber(rs.getString("serialnumber"))
                .production(rs.getString("production"))
                .model(rs.getString("model"))
                .price(rs.getDouble("price"))
                .room(rs.getInt("room"))
                .serv(rs.getString("serv"))
                .city(rs.getString("city"))
                .spisano(rs.getBoolean("spisano"))
                .staydate(rs.getDate("staydate"))
                .year(rs.getInt("year"))
                .ekp(rs.getInt("ekp"))
                .street(rs.getString("street"))
                .number(rs.getString("number"))
                .firstname(rs.getString("firstname"))
                .lastname(rs.getString("lastname"))
                .username(rs.getString("username"))
                .build();
    }
}

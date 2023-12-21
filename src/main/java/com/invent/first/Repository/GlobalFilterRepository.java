package com.invent.first.Repository;
import com.invent.first.response.FilteredJsonResponse;
import com.invent.first.response.FilteredJsonResponseRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public class GlobalFilterRepository{
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    public GlobalFilterRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    //формирует таблицу Result , сопоставление происходит через filteredJsonResponse
    public List<FilteredJsonResponse> globalFilterResult(
            String itemType, String iNumber, Integer iCard, String serialNumber,
            String production, String model, Double price, Integer room,
            String serv, String city, Boolean spisano, Date staydate,
            Integer year, Integer ekp, String firstname, String lastname,
            String username) {
        String sqlQuery = """
                SELECT id,item_type ,i_number,i_card, serialnumber,production,model, price, room, serv, city,spisano,staydate,year,ekp,street,number,
                       firstname,lastname,username
                FROM (
                         SELECT c.id,c.i_card, c.i_number, c.serialnumber,prod.name AS production,model.name as model, c.price, c.room, c.serv, ct.name AS city, type.name AS item_type,c.spisano,c.staydate,c.year,
                                location.ekp,location.street,location.number,u.firstname,u.lastname,u.username
                         FROM Computers c
                                  LEFT JOIN invent.city ct ON c.city_id = ct.id
                                  LEFT JOIN invent.item_type type ON c.item_type_id = type.id
                                  LEFT JOIN invent.location location on c.location_id = location.id
                                  LEFT JOIN invent.productions prod on c.productions_id = prod.id
                                  LEFT JOIN invent.pmodel model on c.model_id = model.id
                                  LEFT JOIN invent._user u on c.user_id = u.id
                         UNION ALL
                                
                         SELECT p.id,p.i_card, p.i_number, p.serialnumber,prod.name AS production,model.name as model, p.price, p.room, p.serv, ct.name, type.name,p.spisano,p.staydate,p.year,
                                location.ekp,location.street,location.number,u.firstname,u.lastname,u.username
                         FROM Printers p
                                  LEFT JOIN invent.city ct ON p.city_id = ct.id
                                  LEFT JOIN invent.item_type type ON p.item_type_id = type.id
                                  LEFT JOIN invent.location location on p.location_id = location.id
                                  LEFT JOIN invent.productions prod on p.productions_id = prod.id
                                  LEFT JOIN invent.pmodel model on p.model_id = model.id
                                  LEFT JOIN invent._user u on p.user_id = u.id
                         UNION ALL
                                
                         SELECT m.id,m.i_card, m.i_number, m.serialnumber,prod.name AS production,model.name as model, m.price, m.room, m.serv, ct.name, type.name,m.spisano,m.staydate,m.year,
                                location.ekp,location.street,location.number,u.firstname,u.lastname,u.username
                         FROM Monitors m
                                  LEFT JOIN invent.city ct ON m.city_id = ct.id
                                  LEFT JOIN invent.item_type type ON m.item_type_id = type.id
                                  LEFT JOIN invent.location location on m.location_id = location.id
                                  LEFT JOIN invent.productions prod on m.productions_id = prod.id
                                  LEFT JOIN invent.pmodel model on m.model_id = model.id
                                  LEFT JOIN invent._user u on m.user_id = u.id
                         UNION ALL
                                
                         SELECT s.id,s.i_card, s.i_number, s.serialnumber,prod.name AS production,model.name as model, s.price, s.room, s.serv, ct.name, type.name,s.spisano,s.staydate,s.year,
                                location.ekp,location.street,location.number,u.firstname,u.lastname,u.username
                         FROM Servereqs s
                                  LEFT JOIN invent.city ct ON s.city_id = ct.id
                                  LEFT JOIN invent.item_type type ON s.item_type_id = type.id
                                  LEFT JOIN invent.location location on s.location_id = location.id
                                  LEFT JOIN invent.productions prod on s.productions_id = prod.id
                                  LEFT JOIN invent.pmodel model on s.model_id = model.id
                                  LEFT JOIN invent._user u on s.user_id = u.id
                         UNION ALL
                                
                         SELECT ps.id,ps.i_card, ps.i_number, ps.serialnumber,prod.name AS production,model.name as model, ps.price, ps.room, ps.serv, ct.name, type.name,ps.spisano,ps.staydate,ps.year,
                                location.ekp,location.street,location.number,u.firstname,u.lastname,u.username
                         FROM Powersystems ps
                                  LEFT JOIN invent.city ct ON ps.city_id = ct.id
                                  LEFT JOIN invent.item_type type ON ps.item_type_id = type.id
                                  LEFT JOIN invent.location location on ps.location_id = location.id
                                  LEFT JOIN invent.productions prod on ps.productions_id = prod.id
                                  LEFT JOIN invent.pmodel model on ps.model_id = model.id
                                  LEFT JOIN invent._user u on ps.user_id = u.id
                         UNION ALL
                                
                         SELECT t.id,t.i_card, t.i_number, t.serialnumber,prod.name AS production,model.name as model, t.price, t.room, t.serv, ct.name, type.name,t.spisano,t.staydate,t.year,
                                location.ekp,location.street,location.number,u.firstname,u.lastname,u.username
                         FROM Telephones t
                                  LEFT JOIN invent.city ct ON t.city_id = ct.id
                                  LEFT JOIN invent.item_type type ON t.item_type_id = type.id
                                  LEFT JOIN invent.location location on t.location_id = location.id
                                  LEFT JOIN invent.productions prod on t.productions_id = prod.id
                                  LEFT JOIN invent.pmodel model on t.model_id = model.id
                                  LEFT JOIN invent._user u on t.user_id = u.id
                     ) result
                WHERE 1=1
                AND (item_type = :itemType OR :itemType IS NULL)
                AND (result.i_number = :iNumber OR :iNumber IS NULL)
                AND (result.i_card = :iCard OR :iCard IS NULL)
                AND (result.serialnumber = :serialNumber OR :serialNumber IS NULL)
                AND (production = :production or :production IS NULL)
                AND (model = :model or :model IS NULL)
                AND (result.price = :price or :price IS NULL)
                AND (result.room = :room or :room IS NULL)
                AND (result.serv = :serv or :serv IS NULL)
                AND (city = :city or :city IS NULL)
                AND (result.spisano = :spisano or :spisano IS NULL)
                AND (result.staydate = :staydate or :staydate IS NULL)
                AND (result.year = :year or :year IS NULL)
                AND (result.ekp = :ekp or :ekp IS NULL)
                AND (firstname = :firstname or :firstname IS NULL)
                AND (lastname = :lastname or :lastname IS NULL)
                AND (username = :username or :username IS NULL)
                ORDER BY id
                """;
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("itemType", itemType)
                .addValue("iNumber", iNumber)
                .addValue("iCard", iCard)
                .addValue("serialNumber", serialNumber)
                .addValue("production", production)
                .addValue("model", model)
                .addValue("price", price)
                .addValue("room", room)
                .addValue("serv", serv)
                .addValue("city", city)
                .addValue("spisano", spisano)
                .addValue("staydate", staydate)
                .addValue("year", year)
                .addValue("ekp", ekp)
                .addValue("firstname", firstname)
                .addValue("lastname", lastname)
                .addValue("username", username);

        return namedParameterJdbcTemplate.query(sqlQuery, parameters, new FilteredJsonResponseRowMapper());
    }

}

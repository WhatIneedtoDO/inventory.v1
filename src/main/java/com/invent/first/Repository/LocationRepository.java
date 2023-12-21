package com.invent.first.Repository;

import com.invent.first.Entity.Location;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {
    //SELECT Запрос для объединения всех таблиц и сортировки по адрессу.
    @Query(value = """
            SELECT
                    l.ekp,
                    ct.name AS city,
                    l.street,
                    l.number,
                    equipment.id AS equipment_id,
                    it.name AS itemtype,
                    equipment.i_number,
                    equipment.serialnumber,
                    equipment.i_card,
                    equipment.year,
                    equipment.serv,
                    equipment.room,
                    equipment.price,
                    equipment.comment,
                    equipment.spisano,
                    equipment.staydate,
                    COALESCE(pr.name, '') AS production,
                    COALESCE(mo.name, '') AS model,
                    COALESCE(u.firstname, '') AS firstname,
                    COALESCE(u.lastname, '') AS lastname
            FROM location l
                         LEFT JOIN (
                    SELECT c.id, c.i_number, c.serialnumber, c.i_card, c.year, c.serv, c.room, c.price, c.comment, c.spisano, c.staydate,
                           c.location_id, c.item_type_id, c.productions_id, c.model_id, c.user_id, c.city_id
                    FROM invent.computers c
                
                    UNION ALL
                
                    SELECT m.id, m.i_number, m.serialnumber, m.i_card, m.year, m.serv, m.room, m.price, m.comment, m.spisano, m.staydate,
                           m.location_id, m.item_type_id, m.productions_id, m.model_id, m.user_id, m.city_id
                    FROM invent.monitors m
                
                    UNION ALL
                
                    SELECT seq.id, seq.i_number, seq.serialnumber, seq.i_card, seq.year, seq.serv, seq.room, seq.price, seq.comment, seq.spisano, seq.staydate,
                           seq.location_id, seq.item_type_id, seq.productions_id, seq.model_id, seq.user_id, seq.city_id
                    FROM invent.servereqs seq
                
                    UNION ALL
                
                    SELECT ps.id, ps.i_number, ps.serialnumber, ps.i_card, ps.year, ps.serv, ps.room, ps.price, ps.comment, ps.spisano, ps.staydate,
                           ps.location_id, ps.item_type_id, ps.productions_id, ps.model_id, ps.user_id, ps.city_id
                    FROM invent.powersystems ps
                
                    UNION ALL
                
                    SELECT p.id, p.i_number, p.serialnumber, p.i_card, p.year, p.serv, p.room, p.price, p.comment, p.spisano, p.staydate,
                           p.location_id, p.item_type_id, p.productions_id, p.model_id, p.user_id, p.city_id
                    FROM invent.printers p
                
                    UNION ALL
                
                    SELECT tel.id, tel.i_number, tel.serialnumber, tel.i_card, tel.year, tel.serv, tel.room, tel.price, tel.comment, tel.spisano, tel.staydate,
                           tel.location_id, tel.item_type_id, tel.productions_id, tel.model_id, tel.user_id, tel.city_id
                    FROM invent.telephones tel
                ) AS equipment ON l.id = equipment.location_id
                         LEFT JOIN invent.item_type it ON it.id = equipment.item_type_id
                         LEFT JOIN invent.productions pr ON pr.id = equipment.productions_id
                         LEFT JOIN invent.pmodel mo ON mo.id = equipment.model_id
                         LEFT JOIN invent._user u ON u.id = equipment.user_id
                         LEFT JOIN invent.city ct ON ct.id = equipment.city_id
            WHERE l.ekp = :ekp
            """,nativeQuery = true)
    List<Tuple> getByEkp(Integer ekp);
}

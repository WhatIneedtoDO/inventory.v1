package com.invent.first.Repository;

import com.invent.first.Entity.Location;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {
    @Query(value = """
    SELECT
           l.ekp,
           ct.name AS city_id,
           l.street,l.number,
           it.name AS itemtype,
           CASE
               WHEN c.id IS NOT NULL THEN c.i_number
               WHEN m.id IS NOT NULL THEN m.i_number
               WHEN seq.id IS NOT NULL THEN seq.i_number
               WHEN ps.id IS NOT NULL THEN ps.i_number
               WHEN p.id IS NOT NULL THEN p.i_number
               WHEN tel.id IS NOT NULL THEN tel.i_number
               ELSE NULL
               END AS i_number,
           CASE
               WHEN c.id IS NOT NULL THEN c.serialnumber
               WHEN m.id IS NOT NULL THEN m.serialnumber
               WHEN seq.id IS NOT NULL THEN seq.serialnumber
               WHEN ps.id IS NOT NULL THEN ps.serialnumber
               WHEN p.id IS NOT NULL THEN p.serialnumber
               WHEN tel.id IS NOT NULL THEN tel.serialnumber
               ELSE NULL
               END AS serialnumber,
           CASE
               WHEN c.id IS NOT NULL THEN c.year
               WHEN m.id IS NOT NULL THEN m.year
               WHEN seq.id IS NOT NULL THEN seq.year
               WHEN ps.id IS NOT NULL THEN ps.year
               WHEN p.id IS NOT NULL THEN p.year
               WHEN tel.id IS NOT NULL THEN tel.year
               ELSE NULL
               END AS year,
           CASE
               WHEN c.id IS NOT NULL THEN c.serv
               WHEN m.id IS NOT NULL THEN m.serv
               WHEN seq.id IS NOT NULL THEN seq.serv
               WHEN ps.id IS NOT NULL THEN ps.serv
               WHEN p.id IS NOT NULL THEN p.serv
               WHEN tel.id IS NOT NULL THEN tel.serv
               ELSE NULL
               END AS serv,
           CASE
               WHEN c.id IS NOT NULL THEN c.room
               WHEN m.id IS NOT NULL THEN m.room
               WHEN seq.id IS NOT NULL THEN seq.room
               WHEN ps.id IS NOT NULL THEN ps.room
               WHEN p.id IS NOT NULL THEN p.room
               WHEN tel.id IS NOT NULL THEN tel.room
               ELSE NULL
               END AS room,
           CASE
               WHEN c.id IS NOT NULL THEN c.price
               WHEN m.id IS NOT NULL THEN m.price
               WHEN seq.id IS NOT NULL THEN seq.price
               WHEN ps.id IS NOT NULL THEN ps.price
               WHEN p.id IS NOT NULL THEN p.price
               WHEN tel.id IS NOT NULL THEN tel.price
               ELSE NULL
               END AS price,
           CASE
               WHEN c.id IS NOT NULL THEN c.comment
               WHEN m.id IS NOT NULL THEN m.comment
               WHEN seq.id IS NOT NULL THEN seq.comment
               WHEN ps.id IS NOT NULL THEN ps.comment
               WHEN p.id IS NOT NULL THEN p.comment
               WHEN tel.id IS NOT NULL THEN tel.comment
               ELSE NULL
               END AS comment,
           CASE
               WHEN c.id IS NOT NULL THEN c.spisano
               WHEN m.id IS NOT NULL THEN m.spisano
               WHEN seq.id IS NOT NULL THEN seq.spisano
               WHEN ps.id IS NOT NULL THEN ps.spisano
               WHEN p.id IS NOT NULL THEN p.spisano
               WHEN tel.id IS NOT NULL THEN tel.spisano
               ELSE NULL
               END AS spisano,
           CASE
               WHEN c.id IS NOT NULL THEN c.staydate
               WHEN m.id IS NOT NULL THEN m.staydate
               WHEN seq.id IS NOT NULL THEN seq.staydate
               WHEN ps.id IS NOT NULL THEN ps.staydate
               WHEN p.id IS NOT NULL THEN p.staydate
               WHEN tel.id IS NOT NULL THEN tel.staydate
               ELSE NULL
               END AS staydate,
           pr.name AS productions_id,
           mo.name AS model_id,
           u.firstname AS firstname,
           u.lastname AS lastname
    
    FROM location l
        left join invent.computers c on l.id = c.location_id
        left join invent.monitors m on l.id = m.location_id
        left join invent.powersystems ps on l.id = ps.location_id
        left join invent.printers p on l.id = p.location_id
        left join invent.servereqs seq on l.id = seq.location_id
        left join invent.telephones tel on l.id = tel.location_id
        LEFT JOIN invent.item_type it ON it.id = COALESCE(c.item_type_id,m.item_type_id,p.item_type_id,tel.item_type_id,seq.item_type_id,ps.item_type_id)
        LEFT JOIN invent.productions pr ON pr.id = COALESCE(c.productions_id, m.productions_id, p.productions_id, tel.productions_id,seq.productions_id,ps.productions_id)
        LEFT JOIN invent.pmodel mo ON mo.id = COALESCE(c.model_id,m.model_id,p.model_id,tel.model_id,seq.model_id,ps.model_id)
        LEFT JOIN invent._user u on u.id = COALESCE(c.user_id,m.user_id,p.user_id,tel.user_id,seq.user_id,ps.user_id)
        LEFT JOIN invent.city ct on ct.id = COALESCE(c.city_id,m.city_id,p.city_id,tel.city_id,seq.city_id,ps.city_id)
    WHERE ekp = :ekp
    """,nativeQuery = true)
    List<Tuple> getByEkp(Integer ekp);
}

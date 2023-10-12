package com.invent.first.Repository;

import com.invent.first.Entity.Trash;
import com.invent.first.response.TrashJsonResponse;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrashRepository extends JpaRepository<Trash,Integer> {

    //Запрос , который по данным из тааблицы Trash подтягивает все остальные поля в зависимости от типа оборудования
    @Query(value = """
    SELECT
        t.id AS trash_id,
        t.equipment_id,
        t.trash_date,
        it.name AS itemtype,
        CASE
            WHEN t.item_type_id = 1 THEN c.i_number
            WHEN t.item_type_id = 2 THEN m.i_number
            WHEN (t.item_type_id = 3 OR t.item_type_id = 4) THEN seq.i_number
            WHEN t.item_type_id = 6 THEN p.i_number
            WHEN t.item_type_id = 7 THEN tel.i_number
            ELSE NULL
            END AS i_number,
        CASE
            WHEN t.item_type_id = 1 THEN c.i_card
            WHEN t.item_type_id = 2 THEN m.i_card
            WHEN (t.item_type_id = 3 OR t.item_type_id = 4) THEN seq.i_card
            WHEN t.item_type_id = 6 THEN p.i_card
            WHEN t.item_type_id = 7 THEN tel.i_card
            ELSE NULL
            END AS i_card,
        CASE
            WHEN t.item_type_id = 1 THEN c.serialnumber
            WHEN t.item_type_id = 2 THEN m.serialnumber
            WHEN (t.item_type_id = 3 OR t.item_type_id = 4) THEN seq.serialnumber
            WHEN t.item_type_id = 6 THEN p.serialnumber
            WHEN t.item_type_id = 7 THEN tel.serialnumber
            ELSE NULL
            END AS serialnumber,
        CASE
            WHEN t.item_type_id = 1 THEN c.price
            WHEN t.item_type_id = 2 THEN m.price
            WHEN (t.item_type_id = 3 OR t.item_type_id = 4) THEN seq.price 
            WHEN t.item_type_id = 6 THEN p.price
            WHEN t.item_type_id = 7 THEN tel.price
            ELSE NULL
            END AS price,
        CASE
            WHEN t.item_type_id = 1 THEN c.serv
            WHEN t.item_type_id = 2 THEN m.serv
            WHEN (t.item_type_id = 3 OR t.item_type_id = 4) THEN seq.serv 
            WHEN t.item_type_id = 6 THEN p.serv
            WHEN t.item_type_id = 7 THEN tel.serv
            ELSE NULL
            END AS serv, 
       
        CASE
            WHEN t.item_type_id = 1 THEN c.room
            WHEN t.item_type_id = 2 THEN m.room
            WHEN (t.item_type_id = 3 OR t.item_type_id = 4) THEN seq.room 
            WHEN t.item_type_id = 6 THEN p.room
            WHEN t.item_type_id = 7 THEN tel.room
            ELSE NULL
            END AS room,
        CASE
            WHEN t.item_type_id = 1 THEN c.comment
            WHEN t.item_type_id = 2 THEN m.comment
            WHEN (t.item_type_id = 3 OR t.item_type_id = 4) THEN seq.comment 
            WHEN t.item_type_id = 6 THEN p.comment
            WHEN t.item_type_id = 7 THEN tel.comment
            ELSE NULL
            END AS comment,
        CASE
            WHEN t.item_type_id = 1 THEN c.year
            WHEN t.item_type_id = 2 THEN m.year
            WHEN (t.item_type_id = 3 OR t.item_type_id = 4) THEN seq.year 
            WHEN t.item_type_id = 6 THEN p.year
            WHEN t.item_type_id = 7 THEN tel.year
            ELSE NULL
            END AS year,
        CASE
            WHEN t.item_type_id = 1 THEN c.staydate
            WHEN t.item_type_id = 2 THEN m.staydate
            WHEN (t.item_type_id = 3 OR t.item_type_id = 4) THEN seq.staydate 
            WHEN t.item_type_id = 6 THEN p.staydate
            WHEN t.item_type_id = 7 THEN tel.staydate
            ELSE NULL
            END AS staydate,
            ct.name as city,
            l.ekp,l.street,l.number,
            pr.name AS production,
            mo.name AS model,
            u.firstname,u.lastname AS lastname
    FROM trash t
             LEFT JOIN computers c ON t.item_type_id = 1 AND t.equipment_id = c.id
             LEFT JOIN monitors m ON t.item_type_id = 2 AND t.equipment_id = m.id
             LEFT JOIN printers p ON t.item_type_id = 6 AND t.equipment_id = p.id
             LEFT JOIN telephones tel ON t.item_type_id = 7 AND t.equipment_id = tel.id
             LEFT JOIN servereqs seq ON (t.item_type_id = 3 OR t.item_type_id = 4) AND t.equipment_id = seq.id
             LEFT JOIN invent.item_type it ON t.item_type_id = it.id
             LEFT JOIN invent.productions pr ON pr.id = COALESCE(c.productions_id, m.productions_id, p.productions_id, tel.productions_id,seq.productions_id)
             LEFT JOIN invent.pmodel mo ON mo.id = COALESCE(c.model_id,m.model_id,p.model_id,tel.model_id,seq.model_id)
             LEFT JOIN invent._user u on u.id = COALESCE(c.user_id,m.user_id,p.user_id,tel.user_id,seq.user_id)
             LEFT JOIN invent.city ct on ct.id = COALESCE(c.city_id,m.city_id,p.city_id,tel.city_id,seq.city_id)
             LEFT JOIN invent.location l on l.id =COALESCE(c.location_id,m.location_id,p.location_id,tel.location_id,seq.location_id);
    """,nativeQuery = true)
    List<Tuple> getAllTrash();
    //Вывод только для определенного типа оборудования
    // поиск по Itemtypeid и equipmentId
    @Query(value = """
            SELECT t FROM Trash t
            WHERE t.equipment_id = :equipmentId
              AND t.itemType.id = :itemtypeId
            """)
    Optional<Trash> findByEquipmentIdAndItemTypeId(Integer equipmentId, Integer itemtypeId);
    @Modifying
    @Query(value = """
    DELETE FROM trash
    WHERE equipment_id = :equipmentId
      AND item_type_id = :itemtypeId
    """,nativeQuery = true)
    void deleteByEquipment_idAndItemType(Integer equipmentId,Integer itemtypeId);
}

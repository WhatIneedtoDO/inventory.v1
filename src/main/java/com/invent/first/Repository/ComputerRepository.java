package com.invent.first.Repository;


import com.invent.first.Entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ComputerRepository extends JpaRepository<Computer,Integer> {
    Computer save(Computer computer);

    @Query(value = "SELECT c , u.id, u.username, u.firstname, u.lastname FROM Computer c " +
            "JOIN FETCH c.production " +
            "JOIN FETCH c.model " +
            "JOIN FETCH c.itemType " +
            "JOIN FETCH c.city " +
            "JOIN FETCH c.location " +
            "JOIN FETCH c.user u")
    List<Computer> findAllComputersWithDetails();

    //формирует список компьютеров по коду ЕКП
    @Query(value = "SELECT c , u.id, u.username, u.firstname, u.lastname FROM Computer c " +
            "JOIN FETCH c.production " +
            "JOIN FETCH c.model " +
            "JOIN FETCH c.itemType " +
            "JOIN FETCH c.city " +
            "JOIN FETCH c.location l " +
            "JOIN FETCH c.user u " +
            "WHERE l.ekp = :ekp")
    List<Computer> findComputersByEkp(@Param("ekp")Integer ekp);
    //формирует список id компьютеров , где одинаковые инвентарные номера с мониторами
    @Query(value = "SELECT c.id FROM Computer c INNER JOIN Monitor m ON c.i_number = m.i_number")
    @Modifying
    @Transactional
    List<Integer> findPairsToMonitor();


}

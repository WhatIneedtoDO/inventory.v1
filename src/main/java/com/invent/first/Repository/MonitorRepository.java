package com.invent.first.Repository;

import com.invent.first.Entity.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor,Integer> {
    Monitor save(Monitor monitor);
    @Query(value = "SELECT m ,u.id, u.username, u.firstname, u.lastname FROM Monitor m " +
            "JOIN FETCH m.production " +
            "JOIN FETCH m.model " +
            "JOIN FETCH m.itemType " +
            "JOIN FETCH m.city " +
            "JOIN FETCH m.location " +
            "JOIN FETCH m.user u")
    List<Monitor> findAllMonitorsWithDetails();
    @Query(value = "SELECT m ,u.id, u.username, u.firstname, u.lastname FROM Monitor m " +
            "JOIN FETCH m.production " +
            "JOIN FETCH m.model " +
            "JOIN FETCH m.itemType " +
            "JOIN FETCH m.city " +
            "JOIN FETCH m.location l " +
            "JOIN FETCH m.user u " +
            "WHERE l.ekp = :ekp")
    List<Monitor>findByEkp(@Param("ekp")Integer ekp);

    @Query(value = "SELECT m.id FROM Monitor m INNER JOIN Computer c ON m.i_number = c.i_number")
    @Modifying
    @Transactional
    List<Integer> findPairsToComputer();

}

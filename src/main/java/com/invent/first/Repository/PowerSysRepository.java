package com.invent.first.Repository;

import com.invent.first.Entity.Monitor;
import com.invent.first.Entity.PowerSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PowerSysRepository extends JpaRepository<PowerSystem,Integer> {
    @Query(value = "SELECT ps ,u.id, u.username, u.firstname, u.lastname,u.dept  FROM PowerSystem ps " +
            "JOIN FETCH ps.production " +
            "JOIN FETCH ps.model " +
            "JOIN FETCH ps.itemType " +
            "JOIN FETCH ps.city " +
            "JOIN FETCH ps.location " +
            "JOIN FETCH ps.user u")
    List<PowerSystem> findAllWithDetails();
    @Query(value = "SELECT ps , u.id, u.username, u.firstname, u.lastname,u.dept FROM PowerSystem ps " +
            "JOIN FETCH ps.production " +
            "JOIN FETCH ps.model " +
            "JOIN FETCH ps.itemType " +
            "JOIN FETCH ps.city " +
            "JOIN FETCH ps.location l " +
            "JOIN FETCH ps.user u " +
            "WHERE u.dept.id = :deptId")
    List<PowerSystem> findByDept(@Param("deptId")Integer deptId);
}

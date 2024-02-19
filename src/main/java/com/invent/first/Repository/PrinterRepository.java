package com.invent.first.Repository;

import com.invent.first.Entity.Monitor;
import com.invent.first.Entity.Printers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrinterRepository extends JpaRepository<Printers,Integer>  {
    @Query(value = "SELECT p ,u.id, u.username, u.firstname, u.lastname FROM Printers p " +
            "JOIN FETCH p.production " +
            "JOIN FETCH p.model " +
            "JOIN FETCH p.itemType " +
            "JOIN FETCH p.city " +
            "JOIN FETCH p.location " +
            "JOIN FETCH p.user u")
    List<Printers> findAllPrintersWithDetails();
    @Query(value = "SELECT p , u.id, u.username, u.firstname, u.lastname,u.dept FROM Printers p " +
            "JOIN FETCH p.production " +
            "JOIN FETCH p.model " +
            "JOIN FETCH p.itemType " +
            "JOIN FETCH p.city " +
            "JOIN FETCH p.location l " +
            "JOIN FETCH p.user u " +
            "WHERE u.dept.id = :deptId")
    List<Printers> findByDept(@Param("deptId")Integer deptId);
}

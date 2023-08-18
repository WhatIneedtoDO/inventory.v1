package com.alibou.security.Repository;

import com.alibou.security.DTO.OutDTO.MonitorOutDTO;
import com.alibou.security.Entity.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}

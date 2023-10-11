package com.invent.first.Repository;

import com.invent.first.Entity.ServerEqs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerEqsRepository extends JpaRepository<ServerEqs,Integer> {
    @Query(value = "SELECT s , u.id, u.username, u.firstname, u.lastname FROM ServerEqs s " +
            "JOIN FETCH s.production " +
            "JOIN FETCH s.model " +
            "JOIN FETCH s.itemType " +
            "JOIN FETCH s.city " +
            "JOIN FETCH s.location " +
            "JOIN FETCH s.user u")
    List<ServerEqs> findAllServerEqsWithDetails();
}

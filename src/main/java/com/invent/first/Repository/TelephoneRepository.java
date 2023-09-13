package com.invent.first.Repository;

import com.invent.first.Entity.Telephones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephones,Integer> {
    @Query(value = "SELECT t , u.id, u.username, u.firstname, u.lastname FROM Telephones t " +
            "JOIN FETCH t.production " +
            "JOIN FETCH t.model " +
            "JOIN FETCH t.itemType " +
            "JOIN FETCH t.city " +
            "JOIN FETCH t.location " +
            "JOIN FETCH t.user u")
    List<Telephones> findAllTelephonesWithDetails();
}

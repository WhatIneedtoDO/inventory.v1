package com.alibou.security.Repository;


import com.alibou.security.DTO.ComputerDTO;
import com.alibou.security.Entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}

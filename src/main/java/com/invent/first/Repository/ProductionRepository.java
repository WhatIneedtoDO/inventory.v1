package com.invent.first.Repository;

import com.invent.first.Entity.Productions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionRepository extends JpaRepository<Productions,Integer> {
}

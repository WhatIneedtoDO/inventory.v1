package com.alibou.security.Repository;

import com.alibou.security.Entity.Productions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionRepository extends JpaRepository<Productions,Integer> {
}

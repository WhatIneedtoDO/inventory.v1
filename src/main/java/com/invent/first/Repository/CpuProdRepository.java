package com.invent.first.Repository;

import com.invent.first.Entity.CpuProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuProdRepository extends JpaRepository<CpuProduction,Integer> {
}

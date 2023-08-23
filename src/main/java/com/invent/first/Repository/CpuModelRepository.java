package com.invent.first.Repository;

import com.invent.first.Entity.CpuModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuModelRepository extends JpaRepository<CpuModel,Integer> {
}

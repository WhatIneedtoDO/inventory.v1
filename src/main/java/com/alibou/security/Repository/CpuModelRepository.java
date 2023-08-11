package com.alibou.security.Repository;

import com.alibou.security.Entity.CpuModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuModelRepository extends JpaRepository<CpuModel,Integer> {
}

package com.alibou.security.Repository;

import com.alibou.security.Entity.CpuModel;
import com.alibou.security.Entity.CpuProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuProdRepository extends JpaRepository<CpuProduction,Integer> {
}

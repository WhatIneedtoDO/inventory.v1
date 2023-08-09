package com.alibou.security.Repository;

import com.alibou.security.Entity.MotherBProd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotherBProdRepos extends JpaRepository<MotherBProd,Integer> {
}

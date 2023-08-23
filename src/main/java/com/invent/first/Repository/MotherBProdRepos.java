package com.invent.first.Repository;

import com.invent.first.Entity.MotherBProd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotherBProdRepos extends JpaRepository<MotherBProd,Integer> {
}

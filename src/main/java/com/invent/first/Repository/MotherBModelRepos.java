package com.invent.first.Repository;

import com.invent.first.Entity.MotherBModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotherBModelRepos extends JpaRepository<MotherBModel,Integer> {
}

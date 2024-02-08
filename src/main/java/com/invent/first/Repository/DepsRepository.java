package com.invent.first.Repository;

import com.invent.first.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepsRepository extends JpaRepository<Department,Integer> {
}

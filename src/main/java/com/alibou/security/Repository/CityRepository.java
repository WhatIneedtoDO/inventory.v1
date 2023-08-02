package com.alibou.security.Repository;

import com.alibou.security.Entity.City;
import com.alibou.security.Entity.Productions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CityRepository extends JpaRepository<City,Integer> {

}

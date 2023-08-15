package com.alibou.security.Repository;

import com.alibou.security.Entity.HistoryOfChanges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryOfChanges,Integer> {
    @Query("""
            SELECT h FROM HistoryOfChanges h
            WHERE h.equipmentId = :equipmentId
              AND h.itemType = :itemType""")
    HistoryOfChanges findByItemTypeAndEquipment(Integer itemType, Integer equipmentId);
}

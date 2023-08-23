package com.invent.first.Repository;

import com.invent.first.Entity.HistoryOfChanges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryOfChanges,Integer> {
    @Query("""
            SELECT h FROM HistoryOfChanges h
            WHERE h.equipmentId = :equipmentId
              AND h.itemType.id = :itemtypeId
              ORDER BY h.changeDate DESC LIMIT 1""")
    HistoryOfChanges findByItemTypeAndEquipment( Integer equipmentId,Integer itemtypeId);
    @Query("""
            SELECT h FROM HistoryOfChanges h
            WHERE h.equipmentId = :equipmentId
              AND h.itemType.id = :itemtypeId""")
    List<HistoryOfChanges> findByItemTypeAndEquipmentforList(Integer equipmentId, Integer itemtypeId);
}

package com.invent.first.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DynamicQueryService {

    private final EntityManager entityManager;


    @Autowired
    public DynamicQueryService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //формирование кол-ва нерабочих устройств
    private final List<String> validTables = Arrays.asList("computers", "monitors", "serverEqs", "telephones", "powersystems", "printers");
    public int countNotWorked(String tableName) {
        if (!validTables.contains(tableName)) {
            throw new IllegalArgumentException("Invalid table name");
        }
        String queryString = String.format("SELECT COUNT(*) FROM %s WHERE serv = 'HAVEPROBLEM'", tableName);
        Query query = entityManager.createNativeQuery(queryString);
        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }
}

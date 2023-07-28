package com.alibou.security.Service;

import com.alibou.security.Entity.Productions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductionService {
    List<Productions> getAllProductions();
}

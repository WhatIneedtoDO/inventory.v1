package com.alibou.security.Service;

import com.alibou.security.DTO.MotherBProdDTO;
import com.alibou.security.Entity.MotherBProd;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MotherBProdService {
    List<MotherBProdDTO> getAllMotherBProds();
}

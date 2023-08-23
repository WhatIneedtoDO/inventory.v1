package com.invent.first.Service;

import com.invent.first.DTO.MotherBProdDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MotherBProdService {
    List<MotherBProdDTO> getAllMotherBProds();
    MotherBProdDTO getMotherBProdById(Integer id);
}

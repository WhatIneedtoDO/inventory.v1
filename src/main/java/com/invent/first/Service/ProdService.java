package com.invent.first.Service;

import com.invent.first.DTO.ProductionsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProdService {
    List<ProductionsDTO> getAllProds();
    ProductionsDTO getProductionById(Integer id);
}

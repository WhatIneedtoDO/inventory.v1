package com.alibou.security.Service;

import com.alibou.security.DTO.ProductionsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProdService {
    List<ProductionsDTO> getAllProds();
}

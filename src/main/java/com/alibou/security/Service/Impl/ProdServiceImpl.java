package com.alibou.security.Service.Impl;

import com.alibou.security.DTO.ProductionsDTO;
import com.alibou.security.Entity.MotherBProd;
import com.alibou.security.Entity.Productions;
import com.alibou.security.Repository.ProductionRepository;
import com.alibou.security.Service.ProdService;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@PersistenceContext
@Transactional
public class ProdServiceImpl implements ProdService {
    private ProductionRepository prodRepository;
    @Autowired
    public ProdServiceImpl(ProductionRepository prodRepository){
        this.prodRepository = prodRepository;
    }
    @Override
    public List<ProductionsDTO> getAllProds() {
        List<Productions> prodList = prodRepository.findAll();
        return mapToDTOs(prodList);
    }

    private List<ProductionsDTO> mapToDTOs(List<Productions> prodList) {
        return prodList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ProductionsDTO mapToDTO(Productions productions) {
        return ProductionsDTO
                .builder()
                .id(productions.getId())
                .name(productions.getName())
                .build();
    }
}

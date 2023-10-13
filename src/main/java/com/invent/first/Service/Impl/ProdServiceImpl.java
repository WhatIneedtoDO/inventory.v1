package com.invent.first.Service.Impl;

import com.invent.first.DTO.ProductionsDTO;
import com.invent.first.Entity.Productions;
import com.invent.first.Repository.ProductionRepository;
import com.invent.first.Service.ProdService;
import jakarta.persistence.EntityNotFoundException;
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
    private final ProductionRepository prodRepository;
    @Autowired
    public ProdServiceImpl(ProductionRepository prodRepository){
        this.prodRepository = prodRepository;
    }
    @Override
    public List<ProductionsDTO> getAllProds() {
        List<Productions> prodList = prodRepository.findAll();
        return mapToDTOs(prodList);
    }
    public ProductionsDTO getProductionById(Integer id){
        Productions productions = prodRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("production not Found"));
        return mapToDTO(productions);
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

package com.invent.first.Service;

import com.invent.first.DTO.ModelDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModelService {
    List<ModelDTO> getAllModel();
    ModelDTO getModelById(Integer id);
}

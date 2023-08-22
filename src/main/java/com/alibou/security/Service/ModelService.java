package com.alibou.security.Service;

import com.alibou.security.DTO.ModelDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModelService {
    List<ModelDTO> getAllModel();
    ModelDTO getModelById(Integer id);
}

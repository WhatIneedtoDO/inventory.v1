package com.alibou.security.Service;

import com.alibou.security.DTO.MotherBModelDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MotherBModelService {
    List<MotherBModelDTO> getAllMotherBModels();
    MotherBModelDTO getMotherModelById(Integer id);

}

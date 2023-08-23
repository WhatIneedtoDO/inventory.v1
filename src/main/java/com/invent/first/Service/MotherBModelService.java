package com.invent.first.Service;

import com.invent.first.DTO.MotherBModelDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MotherBModelService {
    List<MotherBModelDTO> getAllMotherBModels();
    MotherBModelDTO getMotherModelById(Integer id);

}

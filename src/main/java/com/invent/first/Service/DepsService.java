package com.invent.first.Service;

import com.invent.first.DTO.DepartmentsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepsService {
    List<DepartmentsDTO> getAllDeps();
    DepartmentsDTO getDepsById(Integer id);

}

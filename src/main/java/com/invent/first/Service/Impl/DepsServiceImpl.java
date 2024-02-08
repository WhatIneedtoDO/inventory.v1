package com.invent.first.Service.Impl;

import com.invent.first.DTO.DepartmentsDTO;
import com.invent.first.Entity.Department;
import com.invent.first.Repository.DepsRepository;
import com.invent.first.Service.DepsService;
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
public class DepsServiceImpl implements DepsService {
    private final DepsRepository depsRepository;
    @Autowired
    public DepsServiceImpl(DepsRepository depsRepository){
        this.depsRepository = depsRepository;
    }

    @Override
    public List<DepartmentsDTO> getAllDeps() {
        List<Department> departments = depsRepository.findAll();
        return mapToDTOs(departments);
    }

    @Override
    public DepartmentsDTO getDepsById(Integer id) {
        Department department = depsRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Department Not Found"));
        return mapToDTO(department);
    }
    private List<DepartmentsDTO> mapToDTOs(List<Department> departments){
        return departments.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private DepartmentsDTO mapToDTO(Department department) {
        return DepartmentsDTO
                .builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }
}

package com.alibou.security.Service;

import com.alibou.security.DTO.CpuModelDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CpuModelService {
    List<CpuModelDTO> getAllCpuModel();
}

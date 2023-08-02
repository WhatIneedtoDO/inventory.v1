package com.alibou.security.Service;

import com.alibou.security.DTO.ComputerDTO;
import com.alibou.security.DTO.OutDTO.ComputerOutDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ComputerService {

    List<ComputerOutDTO> getAllComputers();
    ComputerDTO addComputer(ComputerDTO computerDTO);
    List<ComputerOutDTO> getAllComputersWithDetails();
}

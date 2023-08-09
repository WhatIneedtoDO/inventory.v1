package com.alibou.security.Service;

import com.alibou.security.DTO.ComputerDTO;
import com.alibou.security.DTO.OutDTO.ComputerOutDTO;
import com.alibou.security.Entity.Computer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ComputerService {

    ComputerDTO getComputerById(Integer computerId);
    ComputerOutDTO getComputerOutById(Integer computerId);
    Computer updateComputer(Integer computerId, ComputerDTO computerDTO);
    ComputerDTO addComputer(ComputerDTO computerDTO);
    List<ComputerOutDTO> getAllComputersWithDetails();

}

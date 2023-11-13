package com.invent.first.Service;

import com.invent.first.DTO.ComputerDTO;
import com.invent.first.DTO.OutDTO.ComputerOutDTO;
import com.invent.first.Entity.Computer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ComputerService {

    ComputerDTO getComputerById(Integer computerId);
    ComputerOutDTO getComputerOutById(Integer computerId);
    Computer updateComputer(Integer computerId, ComputerDTO computerDTO);
    ComputerDTO addComputer(ComputerDTO computerDTO);
    List<ComputerOutDTO> getAllComputersWithDetails();
    Computer deleteById(Integer computerId);
    List<ComputerOutDTO> getComputersOnEkp(Integer ekp);

}

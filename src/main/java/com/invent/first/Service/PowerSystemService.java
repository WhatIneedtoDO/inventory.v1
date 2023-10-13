package com.invent.first.Service;

import com.invent.first.DTO.OutDTO.PowerSystemOutDTO;
import com.invent.first.DTO.OutDTO.ServerEqsOutDTO;
import com.invent.first.DTO.PowerSystemDTO;
import com.invent.first.DTO.ServerEqsDTO;
import com.invent.first.Entity.PowerSystem;
import com.invent.first.Entity.ServerEqs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PowerSystemService {
    PowerSystemDTO getPSById(Integer psId);
    PowerSystemOutDTO getPSOutById(Integer psId);
    PowerSystem updatePS(Integer psId, PowerSystemDTO powerSystemDTO);
    PowerSystemDTO addPowerSystem(PowerSystemDTO powerSystemDTO);
    List<PowerSystemOutDTO> getAllPSWithDetails();
    PowerSystem deleteById(Integer psId);
}

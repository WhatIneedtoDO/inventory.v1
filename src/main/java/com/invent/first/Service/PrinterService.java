package com.invent.first.Service;


import com.invent.first.DTO.OutDTO.MonitorOutDTO;
import com.invent.first.DTO.OutDTO.PrinterOutDTO;
import com.invent.first.DTO.PrinterDTO;

import com.invent.first.Entity.Printers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrinterService {
    PrinterDTO getPrinterById(Integer printerId);
    PrinterOutDTO getPrinterOutById(Integer printerId);
    Printers updatePrinter(Integer printerId, PrinterDTO printerDTO);
    List<PrinterOutDTO> getByDept(Integer deptId);
    PrinterDTO addPrinter(PrinterDTO printerDTO);
    List<PrinterOutDTO> getAllPrintersWithDetails();
    Printers deleteById(Integer printerId);
}

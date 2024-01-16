package com.invent.first.Service;

import com.invent.first.DTO.OutDTO.ComputerOutDTO;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface ExcelExportService<T> {

    public void exportToExcel(List<T> data, Class<T> dtoClass, HttpServletResponse response);
}

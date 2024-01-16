package com.invent.first.Service.Impl;

import com.invent.first.Service.ExcelExportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Service
public class ExcelExportServiceImpl<T> implements ExcelExportService<T> {

    @SneakyThrows
    @Override
    public void exportToExcel(List<T> data, Class<T> dtoClass, HttpServletResponse response) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=data.xlsx");
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Лист1");

            Field[] fields = dtoClass.getDeclaredFields();

            // Создание заголовков
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                String fieldName = field.getName();
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(fieldName);
            }

            // Заполнение данными
            for (int rowIndex = 1; rowIndex <= data.size(); rowIndex++) {
                Row dataRow = sheet.createRow(rowIndex);
                T dtoObject = data.get(rowIndex - 1);
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    Object value = field.get(dtoObject);
                    Cell cell = dataRow.createCell(i);
                    if (value != null) {
                        if (field.getType().equals(Integer.class) || field.getType().equals(Long.class)) {
                            if (field.getName().equals("id")) {
                                String formattedId = "" + value;
                                cell.setCellValue(formattedId);
                            }
                            // ... Другие типы данных, которые требуют форматирования
                        } else if (field.getName().equals("production") || field.getName().equals("model")
                                || field.getName().equals("motherBProd") || field.getName().equals("motherBModel")
                                || field.getName().equals("cpuproduction") || field.getName().equals("cpumodel")
                                || field.getName().equals("city") || field.getName().equals("itemType")) {
                            Field nestedField = field.getType().getDeclaredField("name");
                            nestedField.setAccessible(true);
                            Object nestedValue = nestedField.get(value);
                            if (nestedValue != null) {
                                cell.setCellValue(nestedValue.toString());
                            }
                        } else if (field.getName().equals("userId")) {
                            Field usernameField = field.getType().getDeclaredField("username");
                            usernameField.setAccessible(true);
                            Object usernameValue = usernameField.get(value);
                            if (usernameValue != null) {
                                cell.setCellValue(usernameValue.toString());
                            }
                        } else if (field.getName().equals("location")) {
                            Field locationField = field.getType().getDeclaredField("ekp");
                            locationField.setAccessible(true);
                            Object ekpValue = locationField.get(value);
                            if (ekpValue != null) {
                                cell.setCellValue(ekpValue.toString());
                            }
                        } else {
                            cell.setCellValue(value.toString());
                        }
                    }
                }
            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos);
            response.getOutputStream().write(bos.toByteArray());
        } catch (IOException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}

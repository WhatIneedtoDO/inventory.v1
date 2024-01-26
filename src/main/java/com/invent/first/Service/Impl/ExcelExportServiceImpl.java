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
    public void exportToExcel(List<T> data, Class<T> outDtoClass, HttpServletResponse response) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=data.xlsx");
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Лист1");

            Field[] fields = outDtoClass.getDeclaredFields();

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
                        String fieldName = field.getName();

                        switch (fieldName) {
                            case "id" -> {
                                if (field.getType().equals(Integer.class) || field.getType().equals(Long.class)) {
                                    String formattedId = "" + value;
                                    cell.setCellValue(formattedId);
                                }
                            }
                            case "production", "model", "motherBProd", "motherBModel", "cpuproduction", "cpumodel", "city", "itemType" -> {
                                Field nestedField = field.getType().getDeclaredField("name");
                                nestedField.setAccessible(true);
                                Object nestedValue = nestedField.get(value);
                                if (nestedValue != null) {
                                    cell.setCellValue(nestedValue.toString());
                                }
                            }
                            case "userId" -> {
                                Field usernameField = field.getType().getDeclaredField("username");
                                usernameField.setAccessible(true);
                                Object usernameValue = usernameField.get(value);
                                if (usernameValue != null) {
                                    cell.setCellValue(usernameValue.toString());
                                }
                            }
                            case "location" -> {
                                Field locationField = field.getType().getDeclaredField("ekp");
                                locationField.setAccessible(true);
                                Object ekpValue = locationField.get(value);
                                if (ekpValue != null) {
                                    cell.setCellValue(ekpValue.toString());
                                }
                            }
                            case "serviceability","type","color","typePechat","format","printerClass"->{
                                if (field.getType().isEnum()) {
                                    Enum<?> enumValue = (Enum<?>) value;
                                    cell.setCellValue(enumValue.toString());
                                }
                        }

                            default -> cell.setCellValue(value.toString());
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

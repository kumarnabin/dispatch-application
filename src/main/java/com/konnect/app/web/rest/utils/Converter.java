package com.konnect.app.web.rest.utils;

import com.konnect.app.service.dto.ExcelDataDTO;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Converter {

    public List<ExcelDataDTO> convertToExcelDataDTO(List<List<String>> records) {
        List<ExcelDataDTO> excelDataDTOList = new ArrayList<>();

        for (List<String> record : records) {
            ExcelDataDTO excelDataDTO = new ExcelDataDTO();

            // Iterate through each field in ExcelDataDTO using reflection
            for (int i = 1; i <= 27; i++) { // Assuming 27 fields in ExcelDataDTO
                String value = getValueAtIndex(record, i - 1);
                setColumnValue(excelDataDTO, "column" + i, value);
            }

            // Assuming the publication date is available in the record
            excelDataDTO.setPublicationDate(Instant.now());

            excelDataDTOList.add(excelDataDTO);
        }

        return excelDataDTOList;
    }

    // A utility method to safely get value at a specific index in the list
    private String getValueAtIndex(List<String> record, int index) {
        if (index >= 0 && index < record.size()) {
            return record.get(index);
        }
        return null;
    }

    // A utility method to set the value of a specific field using reflection
    private void setColumnValue(ExcelDataDTO excelDataDTO, String fieldName, String value) {
        try {
            Method method = ExcelDataDTO.class.getMethod("set" + fieldName, String.class);
            method.invoke(excelDataDTO, value);
        } catch (Exception e) {
            // Handle reflection-related exceptions
            e.printStackTrace();
        }
    }
}

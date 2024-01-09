package com.konnect.app.web.rest;

import com.konnect.app.repository.ExcelDataRepository;
import com.konnect.app.service.ExcelDataService;
import com.konnect.app.service.dto.ExcelDataDTO;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ImportController {

    private final Logger log = LoggerFactory.getLogger(ExcelDataResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private static final String ENTITY_NAME = "excelData";
    private final ExcelDataService excelDataService;

    private static ExcelDataRepository excelDataRepository;

    public ImportController(ExcelDataService excelDataService, ExcelDataRepository excelDataRepository) {
        this.excelDataService = excelDataService;
        this.excelDataRepository = excelDataRepository;
    }

    @PostMapping("/csv")
    public List<ExcelDataDTO> parseFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (filename != null && (filename.endsWith(".xlsx") || filename.endsWith(".xls"))) {
            List<ExcelDataDTO> excelData = parseExcel(file);
            return excelData;
        } else if (filename != null && filename.endsWith(".csv")) {
            List<ExcelDataDTO> csvData = parseCSV(file);
            return csvData;
        } else {
            throw new IllegalArgumentException("Unsupported file format");
        }
    }

    private List<ExcelDataDTO> parseExcel(MultipartFile file) {
        List<ExcelDataDTO> savedRecords = new ArrayList<>();
        System.out.println("Parsing file: " + file);
        try (InputStream is = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet

            for (Row row : sheet) {
                List<String> record = new ArrayList<>();
                for (Cell cell : row) {
                    record.add(getCellValueAsString(cell));
                }

                boolean conditionsMet = checkConditions(record);
                System.out.println("Parsing condition: " + conditionsMet);
                if (conditionsMet) {
                    try {
                        ExcelDataDTO excelDataDTO = getExcelDataDTO(record);
                        savedRecords.add(excelDataService.save(excelDataDTO));
                    } catch (Exception e) {
                        log.error("Error occurred while saving record: {}", e.getMessage());
                        // Handle save exceptions or skip problematic records
                    }
                }
            }
        } catch (IOException e) {
            log.error("Error occurred while reading Excel file: {}", e.getMessage());
            throw new RuntimeException("Error occurred while reading Excel file", e);
        }
        return savedRecords;
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return sdf.format(cell.getDateCellValue());
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    public List<ExcelDataDTO> parseCSV(MultipartFile file) {
        List<ExcelDataDTO> savedRecords = new ArrayList<>();
        try {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                try (CSVReader csvReader = new CSVReader(reader)) {
                    String[] values;
                    while ((values = csvReader.readNext()) != null) {
                        List<String> record = Arrays.asList(values);
                        boolean conditionsMet = checkConditions(record);
                        if (conditionsMet) {
                            try {
                                ExcelDataDTO excelDataDTO = getExcelDataDTO(record);
                                savedRecords.add(excelDataService.save(excelDataDTO));
                            } catch (Exception e) {
                                log.error("Error occurred while saving record: {}", e.getMessage());
                                // Handle save exceptions or skip problematic records
                            }
                        }
                    }
                }
            }
        } catch (CsvValidationException e) {
            log.error("Error occurred while parsing CSV: {}", e.getMessage());
            throw new RuntimeException("Error occurred while parsing CSV", e);
        } catch (IOException e) {
            log.error("Error occurred while reading file: {}", e.getMessage());
            throw new RuntimeException("Error occurred while reading file", e);
        }

        return savedRecords;
    }

    private static ExcelDataDTO getExcelDataDTO(List<String> record) {
        ExcelDataDTO excelDataDTO = new ExcelDataDTO();
        excelDataDTO.setColumn1(record.get(0));
        excelDataDTO.setColumn2(record.get(1));
        excelDataDTO.setColumn3(record.get(2));
        excelDataDTO.setColumn4(record.get(3));
        excelDataDTO.setColumn5(record.get(4));
        excelDataDTO.setColumn6(record.get(5));
        excelDataDTO.setColumn7(record.get(6));
        excelDataDTO.setColumn8(record.get(7));
        excelDataDTO.setColumn9(record.get(8));
        excelDataDTO.setColumn10(record.get(9));
        excelDataDTO.setColumn11(record.get(10));
        excelDataDTO.setColumn12(record.get(11));
        excelDataDTO.setColumn13(record.get(12));
        excelDataDTO.setColumn14(record.get(13));
        excelDataDTO.setColumn15(record.get(14));
        excelDataDTO.setColumn16(record.get(15));
        excelDataDTO.setColumn17(record.get(16));
        excelDataDTO.setColumn18(record.get(17));
        excelDataDTO.setColumn19(record.get(18));
        excelDataDTO.setColumn20(record.get(19));
        excelDataDTO.setColumn21(record.get(20));
        excelDataDTO.setColumn22(record.get(21));
        excelDataDTO.setColumn23(record.get(22));
        excelDataDTO.setColumn24(record.get(23));
        excelDataDTO.setColumn25(record.get(24));
        excelDataDTO.setColumn26(record.get(25));
        excelDataDTO.setColumn27(record.get(26));
        return excelDataDTO;
    }

    public static boolean checkConditions(List<String> record) {
        int valueIndex = 24; // Adjust indices based on the positions in the list
        int timestampIndex = 17; // Adjust indices based on the positions in the list

        String value = record.get(valueIndex);
        String timestampValue = record.get(timestampIndex);
        boolean exists = excelDataRepository.existsByColumn18AndColumn25(timestampValue, value);

        if (exists) {
            return false;
        }
        if (
            !isEmptyOrNull(value) &&
            !isEmptyOrNull(timestampValue) &&
            (value.contains("SCTY") || timestampValue.matches(".*\\s[a-zA-Z]$") || timestampValue.matches("^\\d{5}\\.\\d+$"))
        ) {
            return true; // If found once, return true
        }

        return false;
    }

    private static boolean isEmptyOrNull(String value) {
        return value == null || value.isEmpty() || value.trim().equalsIgnoreCase("NULL");
    }
}

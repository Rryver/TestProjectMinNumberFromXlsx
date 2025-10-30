package com.kolosov.testprojectminnumberfromxlsx.services;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ExcelService {

    public List<Integer> readExcel(File excelFile) {
        List<Integer> nums = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(excelFile)) {
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                int numericCellValue = (int) row.getCell(0).getNumericCellValue();
                nums.add(numericCellValue);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return nums;
    }
}

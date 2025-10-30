package com.kolosov.testprojectminnumberfromxlsx.controllers;

import com.kolosov.testprojectminnumberfromxlsx.exceptions.BadArgumentException;
import com.kolosov.testprojectminnumberfromxlsx.exceptions.NotFoundException;
import com.kolosov.testprojectminnumberfromxlsx.services.ExcelService;
import com.kolosov.testprojectminnumberfromxlsx.utils.FastSortService;
import com.kolosov.testprojectminnumberfromxlsx.utils.SortService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class Controller {
    private final ExcelService excelService;
    private final SortService sortService;

    @GetMapping
    public ResponseEntity<String> index(@RequestParam("path") String pathToFile, @RequestParam("N") Integer pos) {
        FileSystemResource fileSystemResource = new FileSystemResource(pathToFile);
        File file = fileSystemResource.getFile();
        if (!file.exists()) {
            throw new NotFoundException("Файл не найден");
        }

        List<Integer> nums = excelService.readExcel(file);
        if (nums.size() < pos) {
            throw new BadArgumentException("Кол-во элементов в .xlsx файле меньше чем N");
        }

        nums = sortService.sort(nums);

        return new ResponseEntity<>(nums.get(pos).toString(), HttpStatus.OK);
    }
}

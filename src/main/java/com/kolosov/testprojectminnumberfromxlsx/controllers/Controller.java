package com.kolosov.testprojectminnumberfromxlsx.controllers;

import com.kolosov.testprojectminnumberfromxlsx.exceptions.BadArgumentException;
import com.kolosov.testprojectminnumberfromxlsx.exceptions.NotFoundException;
import com.kolosov.testprojectminnumberfromxlsx.services.ExcelService;
import com.kolosov.testprojectminnumberfromxlsx.services.sort.SortService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
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
    @Parameters({
            @Parameter(name = "N", schema = @Schema(type = "integer", minimum = "1"))
    })
    public ResponseEntity<String> index(@RequestParam("path") String pathToFile,
                                        @RequestParam("N") @Min(1) Integer pos) {
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

        return new ResponseEntity<>(nums.get(pos - 1).toString(), HttpStatus.OK);
    }
}

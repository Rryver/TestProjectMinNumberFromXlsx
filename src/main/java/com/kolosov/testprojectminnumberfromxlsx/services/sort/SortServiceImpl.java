package com.kolosov.testprojectminnumberfromxlsx.services.sort;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SortServiceImpl implements SortService {

    // Сортировка алгоритмом "Быстрая сортировка".
    // Дополнительно: Дублирующиеся значения удаляются
    public List<Integer> sort(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();

        if (numbers.size() <= 1) {
            return numbers;
        }

        int mainIndex = numbers.size() / 2;
        List<Integer> lessThenMain = new ArrayList<>();
        List<Integer> greaterThenMain = new ArrayList<>();
        Integer mainNumber = numbers.get(mainIndex);
        for (int i = 0; i < numbers.size(); i++) {
            if (i == mainIndex) {
                continue;
            }

            Integer num = numbers.get(i);
            if (num < mainNumber) {
                lessThenMain.add(num);
            } else if (num > mainNumber) {
                greaterThenMain.add(num);
            }
        }

        result.addAll(sort(lessThenMain));
        result.add(mainNumber);
        result.addAll(sort(greaterThenMain));

        return result;
    }
}

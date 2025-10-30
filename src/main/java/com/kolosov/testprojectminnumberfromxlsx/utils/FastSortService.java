package com.kolosov.testprojectminnumberfromxlsx.utils;

import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FastSortService implements SortService {

    //Сортировка алгоритмом "Быстрая сортировка"
    public List<Integer> sort(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();

        if (numbers.size() <= 1) {
            return numbers;
        }

        int mainIndex = numbers.size() / 2;
        List<Integer> lessThenMain = new ArrayList<>();
        List<Integer> greaterThenMain = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (i == mainIndex) {
                continue;
            }

            if (numbers.get(i) <= numbers.get(mainIndex)) {
                lessThenMain.add(numbers.get(i));
            } else {
                greaterThenMain.add(numbers.get(i));
            }
        }

        result.addAll(sort(lessThenMain));
        result.add(numbers.get(mainIndex));
        result.addAll(sort(greaterThenMain));

        return result;
    }
}

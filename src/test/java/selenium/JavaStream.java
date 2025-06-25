package selenium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.checkerframework.checker.units.qual.s;

public class JavaStream {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> resultMoreThanFive = new ArrayList<>();

// Looping Biasa
        // insert number > 5 into result
        System.out.println("Ini menggunakan looping biasa untuk filter number > 5");
        for (Integer number : numbers) {
            if (number > 5) {
                resultMoreThanFive.add(number);
            }
        }
        System.out.println("Numbers greater than 5: " + resultMoreThanFive);
    
// Pake Java Stream untuk filter number > 5 
        System.out.println("Ini menggunakan Java Stream untuk filter number > 5");
        resultMoreThanFive = numbers.stream().filter(integer -> integer > 5).collect(Collectors.toList());
        System.out.println("Numbers greater than 5: " + resultMoreThanFive);
    }
}

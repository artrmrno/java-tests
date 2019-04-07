package com.artrmrno.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


public class QuickTests {

    public static void main(String[] args) {
        // method reference - method isHidden is passed as reference to listFiles
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
        System.out.println(Arrays.toString(hiddenFiles));

        // use of lambda to filter a list of strings (anonymous functions)
        List<String> strings = new ArrayList<String>();
        strings.add("Hello");
        strings.add("Hi");
        strings.add("Hola");
        strings.add("Holanda");
        strings.add("Adios");
        strings.add("Bye");
        List<String> result = filterStrings(strings, (String s) -> s.startsWith("B"));
        System.out.println(result);

        // use streams to filter and group elements
        // filters strings starting with 'H' and group them by their ending letter
        Map<String, List<String>> stringsByLetter = strings.stream()
                .filter((String s) -> s.startsWith("H"))
                .collect(groupingBy((String s) -> s.substring(s.length() - 1)));
        System.out.println(stringsByLetter);

        // filter list with a stream in parallel
        List<String> filteredStrings = strings.parallelStream()
                .filter((String s) -> s.startsWith("H"))
                .collect(Collectors.toList());
        System.out.println(filteredStrings);
    }

    static List<String> filterStrings(List<String> strings, Predicate<String> p) {
        List<String> result = new ArrayList<String>();
        for (String string : strings) {
            if (p.test(string)) {
                result.add(string);
            }
        }
        return result;
    }
}

package javapack;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;


import static java.util.stream.Collectors.toList;


public class Main {

    public static void main(String[] args) throws Exception {
        main_for_test(new InputString(System.in, System.out));
    }
    public static void main_for_test(InputString todo) throws IOException {
        String fileName = todo.input("Enter file name (example - files/file.csv): ");
        String separator = todo.input("Enter separator: ");
        Filereader fileReader = new Filereader();
        List<String> lines = fileReader.read(fileName).collect(toList());
        List<String> parsedLines = new ArrayList<>();
        lines.forEach(line -> parsedLines.add(Parser.LineParser(line, separator, ',')));
        File file = new File("result.txt");
        String absolutePath = file.getAbsolutePath();
        Files.write(Paths.get(absolutePath), parsedLines);
        System.out.println("Path to the file: ");
        System.out.println(absolutePath);
    }
}


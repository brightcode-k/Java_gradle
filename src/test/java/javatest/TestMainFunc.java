package javatest;

import javapack.InputString;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static javapack.Main.main_for_test;

class TestMainFunc {

    @Test
    void main() throws IOException {
        InputString testing_input = mock(InputString.class);
        when(testing_input.input("Enter file name (example - files/file.csv): ")).thenReturn("file.csv");
        when(testing_input.input("Enter separator: ")).thenReturn("+");

        main_for_test(testing_input);

        verify(testing_input).input("Enter file name (example - files/file.csv): ");
        verify(testing_input).input("Enter separator: ");

        File file = new File("result.txt");
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while(scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        assertEquals("4+7", lines.get(0));
        assertEquals("4+0+7", lines.get(1));
        assertEquals("2+2+0+9", lines.get(2));

    }
}

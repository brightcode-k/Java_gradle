package javatest;

import javapack.InputString;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static javapack.Main.todoStream;

class TestMainFunc {

    @Test
    void main() throws IOException {
        List<String> expected = new ArrayList<>();
        expected.add("2+2+0+9");
        expected.add("2+2+1+11");
        expected.add("2+2+1+9");
        expected.add("2+2+13");
        expected.add("2+2+10");

        InputString testing_input = mock(InputString.class);

        when(testing_input.input("Enter file directory (example - src/main/resources/Directory3): ")).thenReturn("src/test/resources");
        when(testing_input.input("Enter the number of streams: ")).thenReturn(String.valueOf(4));
        when(testing_input.input("Enter separator: ")).thenReturn("+");

        todoStream(testing_input);

        verify(testing_input).input("Enter file directory (example - src/main/resources/Directory3): ");
        verify(testing_input).input("Enter the number of streams: ");
        verify(testing_input).input("Enter separator: ");
        File file = new File("result.txt");
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        assertTrue(lines.containsAll(expected));
    }
}


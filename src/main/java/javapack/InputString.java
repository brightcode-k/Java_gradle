package javapack;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class InputString {

    private final Scanner scanner;
    private final PrintStream out;

    public InputString(InputStream in, PrintStream out) {
        scanner = new Scanner(in);
        this.out = out;
    }
    public String input(String message) {
        out.println(message);
        return scanner.next();
    }
}
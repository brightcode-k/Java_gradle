package javatest;

import javapack.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ParserTest {

    @Test
    public void test_delimeter_and_separator_but_delimeter_in_column() {
        String line = "'11'|'AU'|'Austr|alia'";

        String result = Parser.LineParser(line, "^", '|');

        assertNotEquals(result, null);
        assertEquals(result.length(), 7);
        assertEquals(result.charAt(1), '^');
        assertEquals(result.charAt(2), '4');
        assertEquals(result.charAt(4), '6');
        assertEquals(result.charAt(6), '5');
    }

    @Test
    public void test_custom_separator_and_quote_but_double_quotes_in_column() {
        String line = "'11'|'AU'|'Aus\"\"tralia'";

        String result = Parser.LineParser(line, "+", '|');

        assertNotEquals(result, null);
        assertEquals(result.length(), 6);
        assertEquals(result.charAt(1), '+');
        assertEquals(result.charAt(2), '4');
        assertEquals(result.charAt(4), '1');
        assertEquals(result.charAt(5), '3');
    }

    @Test
    public void test_parse_of_line_with_default_separator_and_quote() {
        String line = "11,AU,Aus\"\"tralia";

        String result = Parser.LineParser(line, "$", ',');

        assertNotEquals(result, null);
        assertEquals(result.length(), 6);
        assertEquals(result.charAt(1), '$');
        assertEquals(result.charAt(2), '2');
        assertEquals(result.charAt(4), '1');
        assertEquals(result.charAt(5), '1');
    }

    @Test
    public void test_parse_of_line_with_custom_separators_and_default_quote() {
        String line = "11|AU|Aus\"\"tralia";

        String result = Parser.LineParser(line, "+", '|');

        assertNotEquals(result, null);
        assertEquals(result.length(), 6);
        assertEquals(result.charAt(1), '+');
        assertEquals(result.charAt(2), '2');
        assertEquals(result.charAt(4), '1');
        assertEquals(result.charAt(5), '1');
    }

    @Test
    public void test_custom_line_with_custom_separator() {
        String line = "\"abc\"\",a\"bcd\"e";

        String result = Parser.LineParser(line, "&", ',');

        assertNotEquals(result, null);
        assertEquals(result.length(), 3);
        assertEquals(result.charAt(0), '4');
        assertEquals(result.charAt(1), '&');
        assertEquals(result.charAt(2), '7');
    }

    @Test
    public void test_custom_line_with_custom_separator_with_zero_answer() {
        String line = "\"abc\"\",,a\"bcd\"e";

        String result = Parser.LineParser(line, "+", ',');

        assertNotEquals(result, null);
        assertEquals(result.length(), 5);
        assertEquals(result.charAt(0), '4');
        assertEquals(result.charAt(1), '+');
        assertEquals(result.charAt(2), '0');
        assertEquals(result.charAt(3), '+');
        assertEquals(result.charAt(4), '7');
    }

    @Test
    public void test_custom_delimeter() {
        String line = "11|AU|Australia";

        String result = Parser.LineParser(line, "+", '|');

        assertNotEquals(result, null);
        assertEquals(result.length(), 5);
        assertEquals(result.charAt(1), '+');
        assertEquals(result.charAt(2), '2');
        assertEquals(result.charAt(4), '9');
    }

    @Test
    public void test_custom_delimeter_and_separator() {
        String line = "'11'|'AU'|'Australia'";

        String result = Parser.LineParser(line, "#", '|');

        assertNotEquals(result, null);
        assertEquals(result.length(), 6);
        assertEquals(result.charAt(1), '#');
        assertEquals(result.charAt(2), '4');
        assertEquals(result.charAt(4), '1');
        assertEquals(result.charAt(5), '1');
    }

    @Test
    public void test_custom_line_with_custom_separator_with_zero_answer_with_double_commas() {
        String line = "11/\"\"/Australia";

        String result = Parser.LineParser(line, "_", '/');

        assertNotEquals(result, null);
        assertEquals(result.length(), 5);
        assertEquals(result.charAt(0), '2');
        assertEquals(result.charAt(1), '_');
        assertEquals(result.charAt(2), '0');
        assertEquals(result.charAt(3), '_');
        assertEquals(result.charAt(4), '9');
    }

    @Test
    public void test_custom_line_zero() {
        String line1 = "";

        String result1 = Parser.LineParser(line1, "_", '/');

        assertNotEquals(result1, null);
        assertEquals(result1.length(), 0);
        assertTrue(result1.isEmpty());
    }

    @Test
    public void test_custom_line_with_custom_separator_is_delimeter() {
        String line = "11\"AU\"Australia";

        String result = Parser.LineParser(line, "^", '"');

        assertNotEquals(result, null);
        assertEquals(result.length(), 5);
        assertEquals(result.charAt(0), '2');
        assertEquals(result.charAt(1), '^');
        assertEquals(result.charAt(2), '2');
        assertEquals(result.charAt(3), '^');
        assertEquals(result.charAt(4), '9');
    }

    @Test
    public void test_custom_line_with_custom_separator_is_delimeter_with_double_commas() {
        String line = "11\"\"\"\"Australia";

        String result = Parser.LineParser(line, "=", '"');

        assertNotEquals(result, null);
        assertEquals(result.length(), 9);
        assertEquals(result.charAt(0), '2');
        assertEquals(result.charAt(1), '=');
        assertEquals(result.charAt(2), '0');
        assertEquals(result.charAt(3), '=');
        assertEquals(result.charAt(4), '0');
        assertEquals(result.charAt(5), '=');
        assertEquals(result.charAt(6), '0');
        assertEquals(result.charAt(7), '=');
        assertEquals(result.charAt(8), '9');
    }

    @Test
    public void test_custom_line_with_custom_separator_is_delimeter_with_double_commas_separator_comma() {
        String line = "'11'\"'\"\"'\"'Australia'";

        String result = Parser.LineParser(line, "'", '"');

        assertNotEquals(result, null);
        assertEquals(result.length(), 10);
        assertEquals(result.charAt(0), '4');
        assertEquals(result.charAt(1), '\'');
        assertEquals(result.charAt(2), '1');
        assertEquals(result.charAt(3), '\'');
        assertEquals(result.charAt(4), '0');
        assertEquals(result.charAt(5), '\'');
        assertEquals(result.charAt(6), '1');
        assertEquals(result.charAt(7), '\'');
        assertEquals(result.charAt(8), '1');
        assertEquals(result.charAt(9), '1');
    }

    @Test
    public void test_custom_line_double_commas() {
        String line2 = "\"\"";

        String result2 = Parser.LineParser(line2, "_", '/');

        assertNotEquals(result2, null);
        assertEquals(result2.length(), 0);
        assertTrue(result2.isEmpty());
    }

    @Test
    public void test_custom_line_null() {
        String line3 = null;

        String result3 = Parser.LineParser(line3, "_", '/');

        assertNotEquals(result3, null);
        assertEquals(result3.length(), 0);
        assertTrue(result3.isEmpty());
    }

    @Test
    public void test_custom_line_double_commas_blank() {
        String line = " ";

        String result2 = Parser.LineParser(line, "_", '/');

        assertNotEquals(result2, null);
        assertEquals(result2.length(), 0);
        assertTrue(result2.isBlank());
    }
}




package javatest;

import javapack.Filereader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FilereaderTest {

    private Filereader fileReader;

    @BeforeEach
    public void setUp() {
        fileReader = new Filereader();
    }

    @Test
    void given_country_file_when_read_then_returned_stream_of_lines() throws FileNotFoundException {
        Stream<String> expected = Stream.of("10,AU,,Australia", "11,AU,\"\",Aus\"\"tralia", "\"12\",\"AU\",\" \",\"Australia\"",
                "\"13\",\"AU\",\"Aus \"\" tralia\"", "\"14\",\"AU\",\"Aus,tralia\"");

        Stream<String> actual = fileReader.read("tests.csv");

        assertStreamEquals_for_files(expected, actual);
    }

    @Test
    void given_non_existent_file_when_read_then_file_not_found_exception() {
        assertThrows(FileNotFoundException.class, () -> fileReader.read("INCORRECT"), "\"There is no file with name/missing file: \"INCORRECT\"");
    }

    private static void assertStreamEquals_for_files(Stream<?> first, Stream<?> second) {
        Iterator<?> firstIterator = first.iterator(), secondIterator = second.iterator();
        while (firstIterator.hasNext() && secondIterator.hasNext())
            assertEquals(firstIterator.next(), secondIterator.next());
        assert !firstIterator.hasNext() && !secondIterator.hasNext();
    }
}

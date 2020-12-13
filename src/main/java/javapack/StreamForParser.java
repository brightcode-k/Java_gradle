package javapack;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.System.lineSeparator;

public class StreamForParser {

    public void stream(int numberOfThreads, List<String> lines, String separator) throws IOException {
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        System.out.println("StartOfTheProcess = " + LocalDateTime.now());
        int counter = 0;
        for (String line : lines) {
            service.execute(new MyTask(++counter, line, separator));
        }
        service.shutdown();
        try {
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("EndOfTheProcess = " + LocalDateTime.now());
        Main.synchronizeRows(new File("result.txt"));
    }

    public static class MyTask implements Runnable {

        String line;
        String separator;
        int counter;


        public MyTask(int counter, String line, String separator) {
            this.line = line;
            this.separator = separator;
            this.counter = counter;
        }

        @Override
        public void run() {
            try {
                FileWriter fstream = new FileWriter("result.txt", true);
                BufferedWriter out = new BufferedWriter(fstream);
                out.write(counter+"*"+ Parser.LineParser(line, separator, ',') + lineSeparator());
                out.close();
            } catch (Exception e) {
                System.err.println("Error while writing to file: " +
                        e.getMessage());
            }
        }
    }
}

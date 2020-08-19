package concordance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Concordance {
    private static final Pattern WORD_BOUNDARY = Pattern.compile("\\W+");

    public static void main(String[] args) {
        try (Stream<String> input = Files.lines(Path.of("PrideAndPrejudice.txt"))) {
            input
                    .map(String::toLowerCase)
//                    .flatMap(l -> WORD_BOUNDARY.splitAsStream(l))
                    .flatMap(WORD_BOUNDARY::splitAsStream)
                    .filter(s -> !s.isEmpty())
//                    .collect... // build your map...
                    .forEach(System.out::println);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

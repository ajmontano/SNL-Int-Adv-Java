package concordance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Concordance {
    private static final Pattern WORD_BOUNDARY = Pattern.compile("\\W+");

    public static void main(String[] args) {
        final Comparator<Map.Entry<String, Long>> ascending = Map.Entry.comparingByValue();
        final Comparator<Map.Entry<String, Long>> descending = ascending.reversed();

        try (Stream<String> input = Files.lines(Path.of("PrideAndPrejudice.txt"))) {
            input
                    .map(String::toLowerCase)
//                    .flatMap(l -> WORD_BOUNDARY.splitAsStream(l))
                    .flatMap(WORD_BOUNDARY::splitAsStream)
                    .filter(s -> !s.isEmpty())
//                    .collect(Collectors.groupingBy(s -> s)) // build your map...
                    .collect(Collectors.groupingBy(Function.identity(),
                            Collectors.counting()))
                    .entrySet().stream()// build your map...
//                    .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
//                    .sorted(descending)
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .limit(200)
                    .map(e -> String.format("%20s : %5d", e.getKey(), e.getValue()))
                    .forEach(System.out::println);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

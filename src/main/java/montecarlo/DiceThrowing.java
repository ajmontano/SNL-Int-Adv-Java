package montecarlo;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DiceThrowing {
    public static void main(String[] args) {
        Map<Integer, Long> map =  IntStream.generate(() -> ThreadLocalRandom.current().ints(10, 1, 7)
                .sum())
                .limit(10_000_000)
                .boxed()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long biggest = map.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getValue();

        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> String.format("%2d: %s", e.getKey(),
                        Stream.generate(() -> "*")
                .limit(e.getValue() * 90 / biggest)
                .collect(Collectors.joining())))
                .forEachOrdered(System.out::println);
    }
}

package averages;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

final class MutableAverage {
    private double sum;
    private long count;

    private MutableAverage(double sum, long count) {
        this.sum = sum;
        this.count = count;
    }

    public static MutableAverage of(double sum, long count) {
        return new MutableAverage(sum, count);
    }

    public void include(double d) {
        this.sum += d;
        this.count += 1;
    }

    public void merge(MutableAverage other) {
        System.out.println("Calling merge...");
        this.sum += other.sum;
        this.count += other.count;
    }

    public Optional<Double> get() {
        if (count > 0) return Optional.of(sum / count);
        else return Optional.empty();
    }
}

public class CollectAverage {
    public static void main(String[] args) {
        long start = System.nanoTime();
        ThreadLocalRandom.current().doubles(6_000_000_000L, -Math.PI, +Math.PI)
                .parallel()
                .collect(() -> MutableAverage.of(0, 0),
                        MutableAverage::include,
                        MutableAverage::merge
                )
                .get()
        .map(d -> "The average is " + d)
        .ifPresent(System.out::println);

        long time = System.nanoTime() - start;
        System.out.println("Time is " + (time / 1_000_000_000.0));
    }
}

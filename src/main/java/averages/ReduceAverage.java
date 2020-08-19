package averages;

import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

final class ImmutableAverage {
    private final double sum;
    private final long count;

    private ImmutableAverage(double sum, long count) {
        this.sum = sum;
        this.count = count;
    }

    public static ImmutableAverage of(double sum, long count) {
        return new ImmutableAverage(sum, count);
    }

    public ImmutableAverage include(double d) {
        return new ImmutableAverage(this.sum + d, this.count + 1);
    }

    public ImmutableAverage merge(ImmutableAverage other) {
        System.out.println("Calling merge...");
        return new ImmutableAverage(this.sum + other.sum, this.count + other.count);
    }

    public double get() {
        if (count > 0) return sum / count;
        throw new NoSuchElementException("Average of no numbers");
    }
}

public class ReduceAverage {
    public static void main(String[] args) {
        long start = System.nanoTime();
        BiFunction<ImmutableAverage, Double, ImmutableAverage> accumulate =
                (a, d) -> a.include(d);
        BinaryOperator<ImmutableAverage> combiner =
                (a1, a2) -> a1.merge(a2);

        // VM flag to watch JIT Compiler -XX:+PrintCompilation
        /*double average = */
//        var average = Stream.generate(() -> Math.random() * 2 - 1)
//        var average = Stream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, +Math.PI))
        var average = ThreadLocalRandom.current().doubles(1_000_000_000, -Math.PI, +Math.PI)
                .parallel()
                .mapToObj(d -> d)
//                .limit(1_000_000_000)
                .reduce(ImmutableAverage.of(0, 0),
//                        (a, d) -> a.include(d),
//                        accumulate,
                        ImmutableAverage::include,
//                        (a1, a2) -> a1.merge(a2));
//                        combiner
                        ImmutableAverage::merge
                )
                .get();

        long time = System.nanoTime() - start;
        System.out.println("Average is " + average
                + " time is " + (time / 1_000_000_000.0));
    }
}

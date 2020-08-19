package averages;

import java.util.NoSuchElementException;
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
        double average = Stream.generate(() -> Math.random() * 2 - 1)
                .limit(1_000_000_000)




        long time = System.nanoTime() - start;
        System.out.println("Average is " + average
                + " time is " + (time / 1_000_000_000.0));
    }
}

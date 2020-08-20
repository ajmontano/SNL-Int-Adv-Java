package concordance;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

interface ExFunction<E, F> {
    F apply(E e) throws Throwable;
    public static <E, F> Function<E, Optional<F>> wrap(ExFunction<E, F> op) {
        return e -> {
            try {
                return Optional.of(op.apply(e));
            } catch (Throwable t) {
                return Optional.empty();
            }
        };
    }
}

public class HandleExceptions {
    // "Either", find one in the VAVR
    public static Optional<Stream<String>> openFile(String name) {
        try {
            return Optional.of(Files.lines(Path.of(name)));
        } catch (Exception e) {
//            return Optional.empty();
            return Optional.of(Stream.of("*** ERROR: " + e.getMessage()));
        }
    }

    public static void main(String[] args) {
        Stream.of("a.txt", "b.txt", "c.txt")
//                .flatMap(n -> Files.lines(Path.of(n)))
//                .flatMap(HandleExceptions::openFile)
//                .map(HandleExceptions::openFile)
                .map(ExFunction.wrap(n -> Files.lines(Path.of(n))))
                .peek(o -> {
                    if (o.isEmpty()) {
                        System.err.println("****Uh oh, something broke!!");
                    }
                })
                .filter(Optional::isPresent)
                .flatMap(Optional::get)
                .forEach(System.out::println);
    }
}

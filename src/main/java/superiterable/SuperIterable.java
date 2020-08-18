package superiterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

//
//@FunctionalInterface
//interface Criterion<E> {
//    boolean test(E s);
//}
//
//@FunctionalInterface
//interface Transformer<E, F> {
//    F apply(E e);
//}

public class SuperIterable<E> implements Iterable<E> {
    private Iterable<E> self;

    public SuperIterable(Iterable<E> self) {
        this.self = self;
    }

//    public SuperIterable<E> filter(Criterion<E> crit) {
    public SuperIterable<E> filter(Predicate<E> crit) {
        List<E> selected = new ArrayList<>();
        for (E s : self) {
            if (crit.test(s)) { // "delegate" decision about liking or not to an argument object
                selected.add(s);
            }
        }
        return new SuperIterable<>(selected);
    }

    // "Functor"
//    public <F> SuperIterable<F> map(Transformer<E, F> op) {
    public <F> SuperIterable<F> map(Function<E, F> op) {
        List<F> out = new ArrayList<>();
        for (E s : self) {
            out.add(op.apply(s));
        }
        return new SuperIterable<>(out);
    }

    public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op) {
        List<F> out = new ArrayList<>();
//        for (E s : self) {
//            SuperIterable<F> sisf = op.apply(s);
//            for (F f : sisf) {
//                out.add(f);
//            }
//        }
        self.forEach(e -> op.apply(e).forEach(f -> out.add(f)));
        return new SuperIterable<>(out);
    }

//    public void forEvery(Consumer<E> op) {
//        for (E e : self) op.accept(e);
//    }

    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }
}

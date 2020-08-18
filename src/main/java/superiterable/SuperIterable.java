package superiterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@FunctionalInterface
interface Criterion<E> {
    boolean test(E s);
}

@FunctionalInterface
interface Transformer<E, F> {
    F apply(E e);
}

public class SuperIterable<E> implements Iterable<E> {
    private Iterable<E> self;

    public SuperIterable(Iterable<E> self) {
        this.self = self;
    }

    public SuperIterable<E> filter(Criterion<E> crit) {
        List<E> selected = new ArrayList<>();
        for (E s : self) {
            if (crit.test(s)) { // "delegate" decision about liking or not to an argument object
                selected.add(s);
            }
        }
        return new SuperIterable<>(selected);
    }

    // "Functor"
    public <F> SuperIterable<F> map(Transformer<E, F> op) {
        List<F> out = new ArrayList<>();
        for (E s : self) {
            out.add(op.apply(s));
        }
        return new SuperIterable<>(out);
    }

    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }
}

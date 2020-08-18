package students;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface Criterion<E> {
    boolean test(E s);
//    void doStuff();
}

class SmartStudent implements Criterion<Student> {
    @Override
    public boolean test(Student s) {
        return s.getGpa() > 3;
    }
}

class EnthusiaticStudent implements Criterion<Student> {
    @Override
    public boolean test(Student s) {
        return s.getCourses().size() > 3;
    }
}

class LongStringCriterion implements Criterion<String> {

    @Override
    public boolean test(String s) {
        return s.length() > 4;
    }
}

public class School {
    //    public static void printSmartStudents(List<Student> ls) {
//        for (Student s : ls) {
//            if (s.getGpa() > 3) {
//                System.out.println(s);
//            }
//        }
//        System.out.println("------------------------");
//    }
    public static void printAll(List<?> ls) {
        for (Object s : ls) {
            System.out.println(s);
        }
        System.out.println("------------------------");
    }

//    public static List<Student> getSmartStudents(List<Student> ls, double threshold) {
//        List<Student> selected = new ArrayList<>();
//        for (Student s : ls) {
//            if (s.getGpa() > threshold) { // separate what changes independently...
//                selected.add(s);
//            }
//        }
//        return selected;
//    }
//
    public static <E> List<E> filter(Iterable<E> ls, Criterion<E> crit) {
        List<E> selected = new ArrayList<>();
        for (E s : ls) {
            if (crit.test(s)) { // "delegate" decision about liking or not to an argument object
                selected.add(s);
            }
        }
        return selected;
    }

    public static void main(String[] args) {
        List<Student> roster = List.of(
                new Student("Fred", 3.3, "Math", "Physics"),
                new Student("Jim", 2.3, "Art"),
                new Student("Sheila", 3.9, "Math", "Physics", "Astrophysics", "Quantum mechanics")
        );

//        printSmartStudents(roster);
//        printStudents(getSmartStudents(roster, 3.0));
        printAll(filter(roster, new SmartStudent()));
        printAll(filter(roster, new EnthusiaticStudent()));
//        printAll(filter(List.of("Fred", "Jim", "Sheila"), new LongStringCriterion()));
//        printAll(filter(List.of("Fred", "Jim", "Sheila"), new Criterion<String>() {
//            @Override
//            public boolean test(String s) {
//                return s.length() > 4;
//            }
//        }));
//        printAll(filter(List.of("Fred", "Jim", "Sheila"), /*new Criterion<String>() {*/
//            /*@Override
//            public boolean test*/(String s) -> {
//                return s.length() > 4;
//            }
//        /*}*/));
        // multiple args? MUST specify ALL types, or NO types (java 11+) OR var for everything
//        printAll(filter(List.of("Fred", "Jim", "Sheila"), (String s) -> {
//                return s.length() > 4;
//            }));
//        printAll(filter(List.of("Fred", "Jim", "Sheila"), (s) -> {
//                return s.length() > 4;
//            }));
        // FOR ONE UNTYPED argument leave out the parens
//        printAll(filter(List.of("Fred", "Jim", "Sheila"), s -> {
//                return s.length() > 4;
//            }));
        printAll(filter(List.of("Fred", "Jim", "Sheila"), s -> s.length() > 4 ));

        Criterion<String> obj = s -> s.length() > 4;
        System.out.println("Type of obj is " + obj.getClass().getName());
        System.out.println("obj instanceof Criterion? " + (obj instanceof Criterion));
        Method [] ma = obj.getClass().getMethods();
        for (Method m : ma) {
            System.out.println("> " + m);
        }
    }
}

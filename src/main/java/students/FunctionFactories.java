package students;

import java.util.List;
import java.util.function.Predicate;

public class FunctionFactories {
    public static Predicate<Student> and(Predicate<Student> first, Predicate<Student> second) {
        return s -> first.test(s) && second.test(s);
    }

    public static void main(String[] args) {
        List<Student> roster = List.of(
                new Student("Frederick", 3.3, "Math", "Physics", "Art", "Journalism"),
                new Student("Fred", 3.3, "Math", "Physics"),
                new Student("Jim", 2.3, "Art"),
                new Student("Sheila", 3.9, "Math", "Physics", "Astrophysics", "Quantum mechanics")
        );

        Predicate<Student> smart = Student.getSmartPredicate(3.4);
        Predicate<Student> enthusiastic = s -> s.getCourses().size() > 2;

        roster.stream()
//                .filter(smart)
//                .filter(enthusiastic)
//                .filter(and(smart, enthusiastic))
                .filter(smart.and(enthusiastic))
                .forEach(System.out::println);
    }
}

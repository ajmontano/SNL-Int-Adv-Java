package superiterable;

import students.Student;

import java.util.List;
import java.util.function.Function;

public class UseSuperIterable {
    public static String formatMyStudent(Student s) {
        return s.getName() + " has grade " + s.getGpa();
    }

    public static void main(String[] args) {
        List<Student> listRoster = List.of(
                new Student("Fred", 3.3, "Math", "Physics"),
                new Student("Jim", 2.3, "Art"),
                new Student("Sheila", 3.9, "Math", "Physics", "Astrophysics", "Quantum mechanics")
        );

        SuperIterable<Student> roster = new SuperIterable<>(listRoster);

//        var smart = roster
//                .filter(s -> s.getGpa() > 3)
//                .map(s -> "The student called " + s.getName() + " takes "
//                        + s.getCourses().size() + " courses");
//        for (String s : smart) {
//            System.out.println("> " + s);
//        }

        roster
                .filter(s -> s.getGpa() > 3)
                .map(s -> "The student called " + s.getName() + " takes "
                        + s.getCourses().size() + " courses")
                .forEach(s -> System.out.println(s));

        System.out.println("--------------------------");
        roster
                .flatMap(s -> new SuperIterable<>(s.getCourses()))
                .forEach(s -> System.out.println(s));

        System.out.println("--------------------------");
        roster
                .forEach(s -> System.out.println(s));
        System.out.println("--------------------------");
        roster
                .filter(s -> s.getGpa() > 3)
                .forEach(s -> System.out.println(s));
        System.out.println("--------------------------");
        roster
                .filter(s -> s.getGpa() > 3)
//                .map(s -> s.getName() + " has grade " + s.getGpa())
//                .map(UseSuperIterable::formatMyStudent)
                .map(s -> UseSuperIterable.formatMyStudent(s))
                .forEach(s -> System.out.println(s));
        System.out.println("--------------------------");
        roster
                .flatMap(s -> new SuperIterable<>(s.getCourses()))
                .forEach(s -> System.out.println(s));
        System.out.println("--------------------------");
        roster
                .flatMap(s ->
                        new SuperIterable<>(s.getCourses()).map(c -> s.getName() + " takes " + c))
                .forEach(s -> System.out.println(s));
        System.out.println("--------------------------");
        roster
                .flatMap(s -> new SuperIterable<>(s.getCourses()))
                .distinct()
                .forEach(System.out::println);
//                .forEach(s -> System.out.println(s));
        System.out.println("--------------------------");
        Function<Student, String> formatIt = s -> s.getName();
        roster
                .filter(s -> s.getGpa() > 3)
                .map(formatIt)
//                .map(Student::getName)
                .forEach(s -> System.out.println(s));

        System.out.println("SuperIterable--------------------------");
        roster
                .peek(System.out::println)
                .filter(s -> s.getGpa() > 3)
                .peek(s -> System.out.println("> " + s))
                .map(s -> s.getName() + " has grade " + s.getGpa())
                .forEach(s -> System.out.println(s));

        System.out.println("Stream--------------------------");
        listRoster.stream()
                .peek(System.out::println)
                .filter(s -> s.getGpa() > 3)
                .peek(s -> System.out.println("> " + s))
                .map(s -> s.getName() + " has grade " + s.getGpa())
                .forEach(s -> System.out.println(s));

    }
}

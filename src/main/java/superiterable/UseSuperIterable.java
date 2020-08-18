package superiterable;

import students.Student;

import java.util.List;

public class UseSuperIterable {
    public static void main(String[] args) {
        SuperIterable<Student> roster = new SuperIterable<>(List.of(
                new Student("Fred", 3.3, "Math", "Physics"),
                new Student("Jim", 2.3, "Art"),
                new Student("Sheila", 3.9, "Math", "Physics", "Astrophysics", "Quantum mechanics")
        ));

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
    }
}

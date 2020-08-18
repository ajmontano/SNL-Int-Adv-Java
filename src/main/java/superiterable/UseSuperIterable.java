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

        var smart = roster
                .filter(s -> s.getGpa() > 3)
                .map(s -> "The student called " + s.getName() + " takes "
                        + s.getCourses().size() + " courses");
        for (String s : smart) {
            System.out.println("> " + s);
        }

//        smart.map(s -> s)
    }
}

package students;

import javafx.scene.Group;

import java.util.List;
import java.util.stream.Collectors;

public class GroupingExamples {
    public static String getLetterGrade(Student s) {
        double gpa = s.getGpa();
        if (gpa > 3.5) return "A";
        if (gpa > 3.0) return "B";
        if (gpa > 2) return "C";
        return "D";
    }

    public static void main(String[] args) {
        List<Student> roster = List.of(
                new Student("Fred", 3.3, "Math", "Physics"),
                new Student("Jim", 2.3, "Art"),
                new Student("Jimmy", 2.4, "Art"),
                new Student("Jimboy", 2.3, "Art"),
                new Student("JimminyCricket", 2.3, "Art"),
                new Student("Sheila", 3.9, "Math", "Physics", "Astrophysics", "Quantum mechanics")
        );


        var map = roster.stream()
                .collect(Collectors.groupingBy(GroupingExamples::getLetterGrade,
                        Collectors.mapping(s -> s.getName() + ":" + s.getGpa(),
                                Collectors.joining(", "))));

        map.entrySet().stream()
                .forEach(System.out::println);

    }
}

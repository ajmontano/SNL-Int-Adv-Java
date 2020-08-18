package students;

import java.util.ArrayList;
import java.util.List;

public class School {
    //    public static void printSmartStudents(List<Student> ls) {
//        for (Student s : ls) {
//            if (s.getGpa() > 3) {
//                System.out.println(s);
//            }
//        }
//        System.out.println("------------------------");
//    }
    public static void printStudents(List<Student> ls) {
        for (Student s : ls) {
            System.out.println(s);
        }
        System.out.println("------------------------");
    }

    public static List<Student> getSmartStudents(List<Student> ls, double threshold) {
        List<Student> selected = new ArrayList<>();
        for (Student s : ls) {
            if (s.getGpa() > threshold) { // separate what changes independently...
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
        printStudents(getSmartStudents(roster, 3.0));
    }
}

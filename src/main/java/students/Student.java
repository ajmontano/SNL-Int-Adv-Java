package students;

import java.util.List;
import java.util.function.Predicate;

final public class Student {
    private String name;
    private double gpa;
    private List<String> courses;

    public Student(String name, double gpa, String ... courses) {
        this.name = name;
        this.gpa = gpa;
        this.courses = List.of(courses);
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public List<String> getCourses() {
        return courses;
    }

    public static Predicate<Student> getSmartPredicate(/*final */double threshold) {
        return s -> s.getGpa() > threshold;
    }

//    public static Predicate<Student> getSmartPredicate(/*final */double threshold) {
////        threshold++; // variable for capturing "closure" MUST BE final or "effectively final"
//        final double iThreshold = threshold;
//        threshold++;
//        final int[] oneValue = {0};
//        oneValue[0]++;
//        return s -> s.getGpa() > iThreshold;
//    }
//
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gpa=" + gpa +
                ", courses=" + courses +
                '}';
    }
}

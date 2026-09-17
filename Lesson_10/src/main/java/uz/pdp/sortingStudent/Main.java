package uz.pdp.sortingStudent;

import lombok.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Main {


    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Kamron", 23, 98));
        students.add(new Student(2, "Abbos", 17, 98));
        students.add(new Student(3, "Sardor", 27, 98));
        students.add(new Student(4, "Ilyos", 21, 98));
        students.add(new Student(5, "Abdulaziz", 32, 98));
        students.add(new Student(6, "Muslim", 26, 98));
        students.add(new Student(7, "Saidalo", 16, 98));

        students.stream()
                .sorted(Comparator.comparing(Student::getAge).thenComparing(Student::getName))
                .forEach(System.out::println);

    }

}


@Data
@AllArgsConstructor
class Student {
    private int id;
    private String name;
    private int age;
    private int grade;
}

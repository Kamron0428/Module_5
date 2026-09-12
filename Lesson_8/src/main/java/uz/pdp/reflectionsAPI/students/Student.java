package uz.pdp.reflectionsAPI.students;


import lombok.ToString;

@ToString
public class Student {
    private final String name;
    private int age;
    private int course;

    private Student(String name, int age, int course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }

    private void learn() {
        System.out.println("I'm learning");
    }

    private int updateAge(int a) {
        return a + this.age;
    }

    private String getName() {
        return name;
    }

    private int getAge() {
        return age;
    }

    private int getCourse() {
        return course;
    }
}

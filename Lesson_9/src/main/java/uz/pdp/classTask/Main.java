package uz.pdp.classTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {


        File file = new File("files/tasks.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            List<Student> students = new ArrayList<>();
            students.add(new Student(UUID.randomUUID(), "Kamron", 23, 3));
            students.add(new Student(UUID.randomUUID(), "Kamron", 23, 3));
            students.add(new Student(UUID.randomUUID(), "Kamron", 23, 3));
            students.add(new Student(UUID.randomUUID(), "Kamron", 23, 3));
            students.add(new Student(UUID.randomUUID(), "Kamron", 23, 3));


            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            Type listType = new TypeToken<ArrayList<Student>>() {}.getType();
            String json = gson.toJson(students, listType);
            writer.write(json);
            writer.newLine();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}


@Data
@AllArgsConstructor
class Student {
    private UUID uuid;
    private String name;
    private int age;
    private int course;

   /* @Override
    public String toString() {
        return """
                1. UUID: %s
                2. Name: %s
                3. Age: %d
                4. Course: %d
                """.formatted(uuid, name, age, course);
    }*/
}
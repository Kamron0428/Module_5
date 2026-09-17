package uz.pdp.annotation;


import lombok.AllArgsConstructor;
import lombok.Data;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface Test {
    String author();
    String date();
}

@Test(author = "Kamron", date = "17-09-2026")
@Data
@AllArgsConstructor

class User{

    public static void main(String[] args) throws Exception {

        Class<?> aClass = Class.forName("uz.pdp.annotation.User");
        if (aClass.isAnnotationPresent(Test.class)) {
            Test info = aClass.getAnnotation(Test.class);
            System.out.println(info.date() + " " + info.author());
        }else {
            System.out.println("Annatatsiya topilmadi!");
        }

    }

}

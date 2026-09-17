package uz.pdp.reflectionAPI;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {

//        User user =new User("Kamron", "123", 23);


        Class<?> aClass = Class.forName("uz.pdp.reflectionAPI.User");
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class, String.class, int.class);
        declaredConstructor.setAccessible(true);
        Object o = declaredConstructor.newInstance("Kamron", "123", 23);

        for (Field declaredField : aClass.getDeclaredFields()) {
            System.out.println(declaredField.getName());
        }

        System.out.println();

        for (Method declaredMethod : aClass.getDeclaredMethods()) {
            System.out.println(declaredMethod.getName());
        }

        System.out.println();

        Field declaredField = aClass.getDeclaredField("age");
        declaredField.setAccessible(true);
        declaredField.set(o, 18);
        System.out.println(declaredField.get(o));

        Method counter = aClass.getDeclaredMethod("ageCounter", int.class);
        counter.setAccessible(true);
        System.out.println(counter.invoke(o, 15));

    }

}


class User {
    private String username;
    private String password;
    private int age;


    private User(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    private int ageCounter(int counter) {
        return this.age + counter;
    }

    private String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", Age=" + age +
                '}';
    }
}

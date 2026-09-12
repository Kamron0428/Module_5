package uz.pdp.reflectionsAPI.students;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

//        Student student = new Student("Kamron",23,3);


        Class<?> aClass = Class.forName("uz.pdp.reflectionsAPI.students.Student");
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class, int.class, int.class);
        declaredConstructor.setAccessible(true);
        Object kamron = declaredConstructor.newInstance("Kamron", 23, 3);
        System.out.println(kamron);


        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }


        Field name = aClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(kamron, "Sardor");
        System.out.println(name.get(kamron));


        Method learn = aClass.getDeclaredMethod("learn");
        learn.setAccessible(true);
        learn.invoke(kamron);


        Method updateAge = aClass.getDeclaredMethod("updateAge", int.class);
        updateAge.setAccessible(true);
        Object invoke = updateAge.invoke(kamron, 5);
        System.out.println(invoke);


    }

}

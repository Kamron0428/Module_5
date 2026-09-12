package uz.pdp.reflectionsAPI.books;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Class<?> aClass = Class.forName("uz.pdp.reflectionsAPI.books.Book");
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class,String.class, int.class);
        declaredConstructor.setAccessible(true);
        Object o = declaredConstructor.newInstance("Book","Book",200);
        System.out.println(o);


        Method counter = aClass.getDeclaredMethod("counter", int.class);
        counter.setAccessible(true);
        Object invoke = counter.invoke(o, 30);
        System.out.println(invoke);


        Field declaredField = aClass.getDeclaredField("title");
        declaredField.setAccessible(true);
        declaredField.set(o, "Askar");
        System.out.println(declaredField.get(o));



    }

}

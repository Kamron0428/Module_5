package uz.pdp.reflectionsAPI;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        MyClass myClass = new MyClass("My message");
//        myClass.hi();

//        Class<MyClass> myClass = MyClass.class;
//        Class<?> aClass = Class.forName("uz.pdp.reflectionsAPI.MyClass");

        Class<? extends MyClass> aClass = myClass.getClass();
//        Constructor<? extends MyClass> declaredConstructor = aClass.getDeclaredConstructor(String.class);
//        declaredConstructor.setAccessible(true);
//        MyClass myClass1 = declaredConstructor.newInstance("My Constructor");
//        myClass1.hi();


        /*Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            declaredMethod.setAccessible(true);
//            declaredMethod.invoke(myClass);
            System.out.println(declaredMethod.getName());
        }*/

        /*Method declaredMethods = aClass.getDeclaredMethod("showMessage");
        declaredMethods.setAccessible(true);
        Object invoke = declaredMethods.invoke(myClass);
        System.out.println(invoke);*/

        /*for (Field declaredField : aClass.getDeclaredFields()) {
            System.out.println(declaredField);
        }*/

        /*Field message = aClass.getDeclaredField("message");
        message.setAccessible(true);
        message.set(myClass, "Hi");
        System.out.println(myClass.getMessage());*/


        /*Method declaredMethods = aClass.getDeclaredMethod("sum", int.class, int.class);
        declaredMethods.setAccessible(true);
        Object invoke = declaredMethods.invoke(myClass, 4, 8);
        System.out.println("Sum: "+invoke);*/


    }

// Student, Car, Book, Animal, Phone
}

package uz.pdp.reflectionsAPI.animals;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {

        Class<?> aClass = Class.forName("uz.pdp.reflectionsAPI.animals.Animal");
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Object o = declaredConstructor.newInstance("Mushuk");
        System.out.println(o);


        Method sound = aClass.getDeclaredMethod("sound");
        sound.setAccessible(true);
        sound.invoke(o);


        Field declaredField = aClass.getDeclaredField("type");
        declaredField.setAccessible(true);
        declaredField.set(o,"Bo'ri");
        System.out.println(declaredField.get(o));


        /*
        Field declaredField = aClass.getDeclaredField("title");
        declaredField.setAccessible(true);
        declaredField.set(o, "Askar");
        System.out.println(declaredField.get(o));*/
    }

}

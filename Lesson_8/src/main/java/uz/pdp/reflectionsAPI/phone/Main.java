package uz.pdp.reflectionsAPI.phone;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {

        Class<?> aClass = Class.forName("uz.pdp.reflectionsAPI.phone.Phone");
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class, String.class);
        declaredConstructor.setAccessible(true);
        Object o = declaredConstructor.newInstance("Kamron", "772659872");
        System.out.println(o);


        Method update = aClass.getDeclaredMethod("updateNums", String.class);
        update.setAccessible(true);
        Object invoke = update.invoke(o, "999999999");
        System.out.println(invoke);


        Field declaredField = aClass.getDeclaredField("name");
        declaredField.setAccessible(true);
        declaredField.set(o, "Ilyos");
        System.out.println(declaredField.get(o));


    }

}

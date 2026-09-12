package uz.pdp.reflectionsAPI.cars;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {

        Class<?> aClass = Class.forName("uz.pdp.reflectionsAPI.cars.Car");
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class, String.class, int.class, double.class);
        declaredConstructor.setAccessible(true);
        Object o = declaredConstructor.newInstance("Chevrolet", "Malibu 2", 2021, 35_000);
        System.out.println(o);

        Method priceDiscount = aClass.getDeclaredMethod("priceDiscount", double.class);
        priceDiscount.setAccessible(true);
        System.out.println(priceDiscount.invoke(o,0.1));


    }

}

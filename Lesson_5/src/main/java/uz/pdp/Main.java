package uz.pdp;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int[] nums = {1, 6, 2, -4, 9, -10, 25};
        int[] array = Arrays.stream(nums)
                .filter(num -> num % 2 == 0)
                .toArray();
//                .forEach(n->System.out.print(n + " | "));
        System.out.println(Arrays.toString(array));

        List<String> names = List.of("Ali", "Vali", "Anvar", "Sardor", "Ali", "Aziz");

        List<String> result = names.stream()
                .filter(name -> name.startsWith("A"))   // 'A' bilan boshlanuvchilar
                .distinct()                                   // Dublikatlarni tozalash ("Ali")
                .map(String::toUpperCase)                     // Katta harflarga o'tkazish
                .sorted()                                     // Alifbo tartibida saralash
                .limit(2)                             // Dastlabki 2 tasini olish
                .toList();                                    // List ko'rinishida yig'ish

        System.out.println(result);
    }
}
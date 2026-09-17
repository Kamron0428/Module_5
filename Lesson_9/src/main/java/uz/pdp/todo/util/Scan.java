package uz.pdp.todo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Scan {

    private static final Scanner scanner = new Scanner(System.in);

    public static int scanInt(String s) {
        while (true) {
            System.out.print(s);
            try {
                String strScan = scanner.nextLine().trim();
                return Integer.parseInt(strScan);
            } catch (NumberFormatException e) {
                System.out.println("Xatolik: Siz raqam o'rniga harf kiritdingiz!");
            }
        }
    }

    public static long scanLong(String s) {
        while (true) {
            System.out.print(s);
            try {
                String strScan = scanner.nextLine().trim();
                return Long.parseLong(strScan);
            } catch (NumberFormatException e) {
                System.out.println("Xatolik: Siz butun son kiritmadingiz!");
            }
        }
    }

    public static String scanStr(String s) {
        System.out.print(s);
        return scanner.nextLine();
    }

    public static UUID scanUUID(String s) {
        while (true) {
            try {
                System.out.print(s);
                String line = scanner.nextLine().trim();
                return UUID.fromString(line);
            } catch (IllegalArgumentException e) {
                System.out.println("Xatolik: Kiritilgan ID UUID formatida emas! (Masalan: 123e4567-e89b-12d3-a456-426614174000)");
            }
        }
    }

    public static List<UUID> readUUIDList(String s) {
        System.out.print(s);
        String[] split = scanner.nextLine().split(",");
        List<UUID> uuids = new ArrayList<>();
        for (String str : split) {
            uuids.add(UUID.fromString(str.trim()));
        }
        return uuids;
    }
}

package uz.pdp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Scan {

    static Scanner scanStr = new Scanner(System.in);
    static Scanner scanUUID = new Scanner(System.in);


    public static int scanInt(String s) {
        while (true) {
            System.out.print(s);
            try {
                String strScan = scanStr.nextLine();
                return Integer.parseInt(strScan);
            } catch (NumberFormatException e) {
                System.out.println("Xatolik: Siz raqam o'rniga xarf kiritdingiz!");
            }
        }
    }



    public static String scanStr(String s) {
        System.out.print(s);
        return scanStr.nextLine();
    }


    public static UUID scanUUID(String s) {
        try {
            System.out.print(s);
            return UUID.fromString(scanUUID.next());
        } catch (IllegalArgumentException e) {
            System.out.println("Xatolik: Kiritilgan ID UUID formatida emas! (Masalan: 123e4567-e89b-12d3-a456-426614174000)");
            return null;
        }
    }

    public static List<UUID> readUUIDList(String s) {
        System.out.print(s);
        String[] split = scanUUID.nextLine().split(", ");
        List<UUID> uuids = new ArrayList<>();
        for (String str : split) {
            uuids.add(UUID.fromString(str));
        }
        return uuids;

    }


}

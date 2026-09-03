package homeTask.task_3;

import java.util.Base64;
import java.util.Scanner;

public class MimeEncDec {
    static Scanner inputStr = new Scanner(System.in);
    static Base64.Encoder encoder = Base64.getMimeEncoder();
    static Base64.Decoder decoder = Base64.getMimeDecoder();

    public static void main(String[] args) {

        firstEncDec();
        secondEncDec();

    }


    private static void secondEncDec() {
        System.out.print("Enter your encode text: ");
        String text = inputStr.nextLine();
        byte[] encode = encoder.encode(text.getBytes());
        String encoded = new String(encode);
        System.out.println(encoded);

        byte[] decoded = decoder.decode(encoded);
        System.out.println(new String(decoded));
    }

    private static void firstEncDec() {
        String text = "PDP (Professional Development Program) — Oʻzbekistonda IT mutaxassislarini tayyorlash va sohani rivojlantirishga ixtisoslashgan yirik IT ekotizimidir. U 2017-yilda oʻquv markazi sifatida tashkil etilgan boʻlib, bugungi kunda oʻz ichiga maktab, akademiya va universitetni qamrab oladi.";
        byte[] encode = encoder.encode(text.getBytes());
        String encodedText = new String(encode);
        System.out.println(encodedText);

        byte[] decode = decoder.decode(encodedText);
        String decodedText = new String(decode);
        System.out.println(decodedText);
    }
}

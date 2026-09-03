package homeTask.task_3;

import java.util.Base64;
import java.util.Scanner;

public class URLEncDec {
    static Scanner inputStr = new Scanner(System.in);
    static Base64.Encoder encoder = Base64.getUrlEncoder();
    static Base64.Decoder decoder = Base64.getUrlDecoder();

    public static void main(String[] args) {

        firstEncDec();
        secondEncDec();


    }

    private static void secondEncDec() {
        System.out.print("Enter your encode url: ");
        String text = inputStr.nextLine();
        byte[] encode = encoder.encode(text.getBytes());
        String encoded = new String(encode);
        System.out.println(encoded);

        byte[] decoded = decoder.decode(encoded);
        System.out.println(new String(decoded));
    }

    private static void firstEncDec() {
        String text = "Wikipedia.com";
        byte[] encode = encoder.encode(text.getBytes());
        String encodedText = new String(encode);
        System.out.println(encodedText);

        byte[] decode = decoder.decode(encodedText);
        String decodedText = new String(decode);
        System.out.println(decodedText);
    }

}

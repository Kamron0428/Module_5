package homeTask.task_3;

import java.util.Base64;
import java.util.Scanner;

public class BasicEncDec {
    static Scanner inputStr = new Scanner(System.in);
    public static void main(String[] args) {

//        firstEncDec();
//        secondEncDec();


    }

    private static void secondEncDec() {
        Base64.Encoder encoder = Base64.getEncoder();
        System.out.print("Enter your encode text: ");
        String text = inputStr.nextLine();
        System.out.println();
        byte[] encode = encoder.encode(text.getBytes());
        String encoded = new String(encode);
        System.out.println(encoded);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(encoded);
        System.out.println(new String(decoded));
    }

    private static void firstEncDec() {
        Base64.Encoder encoder = Base64.getEncoder();
        String text = "Assalomu alekum bratim!";
        byte[] encode = encoder.encode(text.getBytes());
        String encodedText = new String(encode);
        System.out.println(encodedText);

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(encodedText);
        String decodedText = new String(decode);
        System.out.println(decodedText);
    }

}

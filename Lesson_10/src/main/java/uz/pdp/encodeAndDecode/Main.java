package uz.pdp.encodeAndDecode;

import java.beans.Encoder;
import java.util.Base64;

public class Main {

    public static void main(String[] args) {

        textEncodeDecode();
    }

    private static void textEncodeDecode() {
        String text = "This is a test";
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode(text.getBytes());
        String encodeText = new String(encode);
        System.out.println(encodeText);

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(encodeText);
        String decodeText = new String(decode);
        System.out.println(decodeText);
    }

}

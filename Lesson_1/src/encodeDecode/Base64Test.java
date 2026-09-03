package encodeDecode;

import java.util.Base64;

public class Base64Test {

    public static void main(String[] args) {

        basicEncodeDecode(); //Xavfli
        urlEncodeDecode(); //Xavfsiz
        mimeEncoderDecoder(); //eng xavfsiz har 76 xarfdan keyn yangi qatorga o'tadi

    }

    private static void mimeEncoderDecoder() {
        Base64.Encoder encoder = Base64.getMimeEncoder();
        String text = "PDP (Professional Development Program) — Oʻzbekistonda IT mutaxassislarini tayyorlash va sohani rivojlantirishga ixtisoslashgan yirik IT ekotizimidir. U 2017-yilda oʻquv markazi sifatida tashkil etilgan boʻlib, bugungi kunda oʻz ichiga maktab, akademiya va universitetni qamrab oladi.";
        byte[] encode = encoder.encode(text.getBytes());
        String encoded = new String(encode);
        System.out.println(encoded);

        Base64.Decoder decoder = Base64.getMimeDecoder();
        byte[] decode = decoder.decode(encoded);
        String decoded = new String(decode);
        System.out.println(decoded);
    }

    private static void urlEncodeDecode() {
        Base64.Encoder encoder = Base64.getUrlEncoder();
        String text = "pdp.online.uz";
        byte[] encode = encoder.encode(text.getBytes());
        String encoded = new String(encode);
        System.out.println(encoded);

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(encode);
        String decoded = new String(decode);
        System.out.println(decoded);
    }

    private static void basicEncodeDecode() {
        Base64.Encoder encoder = Base64.getEncoder();
        String text = "This is a test";
        byte[] encode = encoder.encode(text.getBytes());
        String encoded = new String(encode);
        System.out.println(encoded);


        Base64.Decoder decoder = Base64.getMimeDecoder();
        byte[] decode = decoder.decode(encode);
        String decoded = new String(decode);
        System.out.println(decoded);
    }

}

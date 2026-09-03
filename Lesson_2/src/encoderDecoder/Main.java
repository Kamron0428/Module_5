package encoderDecoder;

import java.util.Base64;

public class Main {

    public static void main(String[] args) {

        User user1 = new User();
        user1.setId(1L);
        user1.setAge(23);
        user1.setUsername("admin");
        user1.setEmail("omonovkamron77@gmail.com");

        System.out.println(user1);

        String user = user1.toString();
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode(user.getBytes());
        String encoded = new String(encode);
        System.out.println(encoded);


        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(encoded);
        String decoded = new String(decode);
        System.out.println(decoded);

    }

}

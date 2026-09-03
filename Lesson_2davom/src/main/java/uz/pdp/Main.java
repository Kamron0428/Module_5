package uz.pdp;

import java.util.Base64;

public class Main {

    public static void main(String[] args) {

        basicCreateUser();

        builderCreate();


    }

    private static void builderCreate() {
        Religion user = User
                .builder()
                .id(2L)
                .username("username")
                .email("email")
                .age(23)
                .religion("Islam")
                .build();
        System.out.println(user);
    }

    private static void basicCreateUser() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("username");
        user1.setEmail("email");
        user1.setAge(23);
        user1.setReligion("Islam");


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

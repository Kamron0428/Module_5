package uz.pdp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Main {
    public static void main(String[] args) {
//        Gson gson = new Gson();

        Gson gson = new GsonBuilder()
                .create();

        String usersJson = """
                
                  {
                  "uuid": 1,
                  "firstName": "Ali",
                  "lastName": "Sobirov",
                  "email": "ali.sobirov1@example.uz",
                  "phone": "+998901011237",
                  "age": 46,
                  "city" : "Tashkent"
                  }
                
                """;

        User user = gson.fromJson(usersJson, User.class);
        System.out.println(user);


//        usersJson = gson.toJson(usersJson);
//        System.out.println(usersJson);

    }
}
@AllArgsConstructor
@NoArgsConstructor
@Data
class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String age;
    private String city;
}
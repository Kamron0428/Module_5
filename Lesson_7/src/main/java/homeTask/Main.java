package homeTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Since;
import lombok.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        /*
        getAllUsers(client);
        get(client);
        getAndStatusCode(client);
        userPOSTtoJSON(client);
        contentType(client);
        textBlock(client);
        deleteBlock(client);
        getUser(client);
*/


        Gson gson = new Gson();
/*
        fromJsonUsers(gson);
        toJsonUsers(gson);

        */


/*
        Gson gson1 = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        prettyPrintingTest(gson1);
*/
/*
        Gson gson2 = new GsonBuilder()
                .serializeNulls()
                .create();
        serializeNullsTest(gson2);
*/
/*
        Gson gson3 = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        dateFormat(gson3);
*/

        /*
        setVersionTest();
        testPUTMethod(client);
        */


//        getAllUsers(client);


    }

    private static void testPUTMethod(HttpClient client) throws IOException, InterruptedException {
        String json = """
                {
                  "id": 1,
                  "name": "Kamron",
                  "username": "Kama",
                  "email": "kamron@gmail.com",
                  "date": "2020-10-12 20:21:20",
                  "phone": "999999999"
                }
                """;
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/1"))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        System.out.println("Status" + response.statusCode());
        System.out.println(response.body());
    }

    private static void setVersionTest() {
        String json = """
                {
                  "id": 1,
                  "name": "Leanne Graham",
                  "username": "Bret",
                  "email": "Sincere@april.biz",
                  "date": "2020-10-12 20:21:20",
                  "phone": "777777777"
                }
                """;
        Gson v1 = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setVersion(1.0)
                .create();
        Gson v3 = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setVersion(3.0)
                .create();

        User userObj = v3.fromJson(json, User.class);

        System.out.println("--- v1.0 (phone bo'lmasligi kerak) ---");
        System.out.println(v1.toJson(userObj));

        System.out.println("\n--- v3.0 (phone chiqishi kerak) ---");
        System.out.println(v3.toJson(userObj));
    }

    private static void dateFormat(Gson gson3) {
        String user = """
                {
                  "id": 1,
                  "name": "Leanne Graham",
                  "username": "Bret",
                  "email": "Sincere@april.biz"
                  "date": "2020-10-12 20:21:20"
                }
                """;
        System.out.println(gson3.toJson(user));
    }

    private static void serializeNullsTest(Gson gson2) {
        String user = """
                {
                  "id": 1,
                  "name": "Leanne Graham",
                  "username": "Bret",
                  "email": "Sincere@april.biz"
                }
                """;
        String json = gson2.toJson(user);
        System.out.println(json);
    }

    private static void prettyPrintingTest(Gson gson1) {
        String user = """
                {
                  "id": 1,
                  "name": "Leanne Graham",
                  "username": "Bret",
                  "email": "Sincere@april.biz"
                }
                """;
        String json = gson1.toJson(user);
        System.out.println(json);
    }

    private static void toJsonUsers(Gson gson) {
        String json = """
                {
                  "id": 1,
                  "name": "Leanne Graham",
                  "username": "Bret",
                  "email": "Sincere@april.biz"
                }
                """;
        String json1 = gson.toJson(json);
        System.out.println(json1);
    }

    private static void fromJsonUsers(Gson gson) {
        String json = """
                {
                  "id": 1,
                  "name": "Leanne Graham",
                  "username": "Bret",
                  "email": "Sincere@april.biz"
                }
                """;

        User user = gson.fromJson(json, User.class);
        System.out.println(user.getName());
    }

    static void getUser(HttpClient client) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/1"))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    private static void deleteBlock(HttpClient client) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/1"))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: " + response.statusCode());
    }

    private static void textBlock(HttpClient client) throws IOException, InterruptedException {
        String json = """
                {
                    "name": "%s",
                    "username": "%s",
                    "email": "%s"
                }
                """.formatted("Ali", "ali", "ali202@gmail.com");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                // Server body JSON ekanini bilishi uchun Content-Type kerak
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body());
    }

    private static void contentType(HttpClient client) throws IOException, InterruptedException {
        String json = "{\"name\":\"Ali Valiyev\","
                + "\"username\":\"ali\","
                + "\"email\":\"ali@example.com\"}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                // Server body JSON ekanini bilishi uchun Content-Type kerak
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    private static void userPOSTtoJSON(HttpClient client) throws IOException, InterruptedException {
        String json = "{\"name\":\"Ali Valiyev\","
                + "\"username\":\"ali\","
                + "\"email\":\"ali@example.com\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    private static void getAllUsers(HttpClient client) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                .GET()
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    private static void getAndStatusCode(HttpClient client) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/5"))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
    }

    private static void get(HttpClient client) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/1"))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

}


@Data
@AllArgsConstructor
@NoArgsConstructor
class User {
    private String name;
    private String username;
    private String email;
    private Date date =  new Date();

    @Since(2.0)
    private String phone;
}

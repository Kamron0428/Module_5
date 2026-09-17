package uz.pdp.httpClient;

import com.google.gson.*;
import com.google.gson.annotations.Since;
import com.google.gson.reflect.TypeToken;
import lombok.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.*;
import java.net.http.*;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        getInfo();
        toGsonAndfromGson();


    }

    private static void toGsonAndfromGson() {
        Person person = new Person("Kamron", 23, "omonovkamron77@gmail.com");
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();


        String json = gson.toJson(person);
        System.out.println(json);
        Person person1 = gson.fromJson(json, Person.class);
        System.out.println(person1);
    }

    private static void getInfo() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://cbu.uz/uz/arkhiv-kursov-valyut/json/"))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();


        Type currencyType = new TypeToken<List<Currency>>() {
        }.getType();
        List<Currency> list = gson.fromJson(response.body(), currencyType);


        list.stream()
                .limit(10)
                .forEach(System.out::println);
    }


}

@Data
@AllArgsConstructor
class Person{
    private String name;
    private int age;
    private String email;
}

@Data
@AllArgsConstructor
class Currency{
    private int id;
    private String code;
    private String ccy;
    private String CcyNm_RU;
    private String CcyNm_UZ;
    private String CcyNm_UZC;
    private String CcyNm_EN;
    private String Nominal;
    private String Rate;
    private String Diff;
    private String Date;

    @Override
    public String toString() {
        return """
                1. ID: %d
                2. Code: %s
                3. CCY: %s
                4. CcyNm_RU: %s
                5. CcyNm_UZ: %s
                6. CcyNm_UZC: %s
                7. CcyNm_EN: %s
                8. Nominal: %s
                9. Rate: %s
                10. Diff: %s
                11. Date: %s
                """.formatted(id,code,ccy,CcyNm_RU,CcyNm_UZ,CcyNm_UZC,CcyNm_EN,Nominal,Rate,Diff,Date);
    }
}


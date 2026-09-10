package uz.pdp.urlExample;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        URL url = new URL("https://kun.uz");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);

        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            System.out.println(s);
        }

    }
    // Eski klass va bu java 11 versiyasigacha ishlatilgan,
    // java > 11 keyn HttpClient degan class chiqarilgan


}

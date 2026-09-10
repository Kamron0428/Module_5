package uz.pdp.test;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.*;

public class Main {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/MyFirstDB";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "kama0066";

    private static final Gson gson = new Gson();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/users", new UsersHandler());
        server.setExecutor(null);

        System.out.println("Сервер запущен! Откройте в браузере: http://localhost:8080/api/users");
        server.start();
    }

    static class UsersHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                List<User> userList = new ArrayList<>();

                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT id, name, email, age FROM users")) {

                    while (rs.next()) {
                        userList.add(new User(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getInt("age")
                        ));
                    }

                    String jsonResponse = gson.toJson(userList);

                    byte[] responseBytes = jsonResponse.getBytes(StandardCharsets.UTF_8);
                    exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
                    exchange.sendResponseHeaders(200, responseBytes.length);

                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(responseBytes);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    String error = "{\"error\": \"" + e.getMessage() + "\"}";
                    exchange.sendResponseHeaders(500, error.length());
                    exchange.getResponseBody().write(error.getBytes());
                }
            } else {
                exchange.sendResponseHeaders(405, -1); // Метод не поддерживается
            }
        }
    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class User {
    private int id;
    private String name;
    private String email;
    private int age;
}



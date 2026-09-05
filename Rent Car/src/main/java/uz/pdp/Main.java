package uz.pdp;

import uz.pdp.model.Car;
import uz.pdp.repasitoy.*;
import uz.pdp.service.*;
import uz.pdp.ui.ConsoleUI;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        /*FileUserRepository userRepository = new FileUserRepository();
        FileCarRepository carRepository = new FileCarRepository();

        AuthService authService = new AuthServiceImpl(userRepository);
        RentCarService carService = new RentCarServiceImpl(carRepository);

        ConsoleUI consoleUI = new ConsoleUI(authService, carService);
        consoleUI.start();*/

        rentCarToFile();
    }

    private static void rentCarToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("files/cars.txt"))) {
            List<Car> cars = new ArrayList<>();
            cars.add(Car.builder()
                    .id(UUID.randomUUID())
                    .model("Malibu 2")
                    .brand("Chevrolet")
                    .year(2020)
                    .price(1_000_000.00)
                    .isAvailable(true)
                    .build());

             cars.add(Car.builder()
                    .id(UUID.randomUUID())
                    .model("HUN")
                    .brand("BYD")
                    .year(2025)
                    .price(1_500_000.00)
                    .isAvailable(true)
                    .build());

            cars.add(Car.builder()
                    .id(UUID.randomUUID())
                    .model("Jentra")
                    .brand("Chevrolet")
                    .year(2018)
                    .price(500_000.00)
                    .isAvailable(true)
                    .build());

            cars.add(Car.builder()
                    .id(UUID.randomUUID())
                    .model("Cobalt")
                    .brand("Chevrolet")
                    .year(2021)
                    .price(800_000.00)
                    .isAvailable(true)
                    .build());

            cars.add(Car.builder()
                    .id(UUID.randomUUID())
                    .model("M5")
                    .brand("BMW")
                    .year(2022)
                    .price(20_000_000.00)
                    .isAvailable(true)
                    .build());


            bw.write(cars.toString());
            bw.newLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
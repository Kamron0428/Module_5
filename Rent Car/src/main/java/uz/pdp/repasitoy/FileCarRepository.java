package uz.pdp.repasitoy;

import lombok.SneakyThrows;
import uz.pdp.model.Car;
import java.io.*;
import java.util.*;

public class FileCarRepository {
    @SneakyThrows
    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        File file = new File("files/cars.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                String line = br.readLine();
                String[] split = line.split(", ");
                UUID id = UUID.fromString(split[0]);
                String brand = split[1];
                String model = split[2];
                int year = Integer.parseInt(split[3]);
                double pricePerDay = Double.parseDouble(split[4]);
                boolean isAvailable = Boolean.parseBoolean(split[5]);
                UUID renterId = UUID.fromString(split[6]);

                cars.add(new Car(id, brand, model, year, pricePerDay, isAvailable, renterId));
            }
        } catch (IOException e) {
            throw new RuntimeException("Faylni o'qishda xatolik: " + e.getMessage());
        }
        return cars;
    }

    public void saveAll(List<Car> cars) {
        File file = new File("files/cars.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (Car car : cars) {
                String renter = null;
                if (car.getRentID() != null) {
                    renter = car.getRentID().toString();
                }

                String carDate = "%s, %s, %s, %d, %.2f, %b, %s".formatted(
                        car.getId(),
                        car.getBrand(),
                        car.getModel(),
                        car.getYear(),
                        car.getPrice(),
                        car.isAvailable(),
                        renter);

                bw.write(carDate);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Faylga yozishda xatolik: " + e.getMessage());
        }
    }

    public void update(Car updatedCar) {
        List<Car> cars = findAll();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getId().equals(updatedCar.getId())) {
                cars.set(i, updatedCar);
                break;
            }
        }
        saveAll(cars);
    }

    public void save(Car car) {
        List<Car> cars = findAll();
        cars.add(car);
        saveAll(cars);
    }
}

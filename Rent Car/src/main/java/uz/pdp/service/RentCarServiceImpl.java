package uz.pdp.service;

import uz.pdp.model.Car;
import uz.pdp.repasitoy.FileCarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RentCarServiceImpl implements RentCarService {
    private final FileCarRepository fileCarRepository;

    public RentCarServiceImpl(FileCarRepository fileCarRepository) {
        this.fileCarRepository = fileCarRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return fileCarRepository.findAll();
    }

    @Override
    public List<Car> getAvailableCars() {
        List<Car> allCars = getAllCars();
        List<Car> availableCars = new ArrayList<>();

        for (Car car : allCars) {
            if (car.isAvailable()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    @Override
    public List<Car> getRenterId(UUID userId) {
        List<Car> allCars = getAllCars();
        List<Car> userCars = new ArrayList<>();

        for (Car car : allCars) {
            if (!car.isAvailable() && userId.equals(car.getRentID())) {
                userCars.add(car);
            }
        }
        return userCars;
    }

    @Override
    public void borrowCar(UUID userId, UUID carId) {
        List<Car> cars = getAllCars();
        boolean exists = false;
        for (Car car : cars) {
            if (car.getId().equals(carId)) {
                if (!car.isAvailable()) {
                    throw new RuntimeException("Bu avtomobil allaqachon ijaraga berilgan!");
                }
                car.setAvailable(false);
                car.setRentID(userId);
                fileCarRepository.update(car);
                exists = true;
                break;
            }
        }

        if (!exists) {
            throw new RuntimeException("Bunday ID li avtomobil topilmadi!");
        }
    }

    @Override
    public void returnCar(UUID userId, UUID carId) {
        List<Car> cars = getAllCars();
        boolean found = false;

        for (Car car : cars) {
            if (car.getId().equals(carId) && userId.equals(car.getRentID())) {
                car.setAvailable(true);
                car.setRentID(null);
                fileCarRepository.update(car);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new RuntimeException("Sizga tegishli bunday avtomobil topilmadi!");
        }
    }
}
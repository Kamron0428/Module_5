package uz.pdp.service;

import uz.pdp.model.Car;

import java.util.*;

public interface RentCarService {
    List<Car> getAllCars();
    List<Car> getAvailableCars();
    List<Car> getRenterId(UUID userId);
    void borrowCar(UUID userId, UUID carId);
    void returnCar(UUID userId, UUID carId);
}

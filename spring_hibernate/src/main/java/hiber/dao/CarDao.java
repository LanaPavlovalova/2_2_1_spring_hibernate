package hiber.dao;

import hiber.model.Car;

public interface CarDao {
    void add(Car car);
    Car getCarById(Long id);
    void clearCars();
}
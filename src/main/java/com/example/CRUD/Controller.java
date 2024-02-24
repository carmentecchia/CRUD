package com.example.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class Controller {
    @Autowired
    private CarRepository carRepository;

    @PostMapping
    public Car create(@RequestBody Car car) {
        Car createdCar = carRepository.save(car);
        return createdCar;
    }

    @GetMapping
    public List<Car> getCars() {
        List<Car> cars = carRepository.findAll();
        return cars;
    }

    @GetMapping("/{id}")
    public Optional<Car> getACar(@PathVariable Long id) {
        if (carRepository.existsById(id)) {
            Optional<Car> car = carRepository.findById(id);
            return car;
        }
        return null;
    }

    @PutMapping("/{id}")
    public Car updateTypeCar(@PathVariable Long id, @RequestBody String type) {
        if (carRepository.existsById(id)) {
            Car car = carRepository.findById(id).get();
            car.setType(type);
            Car carUpdated = carRepository.save(car);
            return carUpdated;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteACar(@PathVariable Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            System.out.println("Conflict HTTP status");
        }
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllCar() {
        carRepository.deleteAll();
    }
}
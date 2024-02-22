package com.example.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class Controller {
    @Autowired
    private CarRepository carRepository;
    @PostMapping("/add-car")
    public Car newCar(@RequestBody Car car){
        return carRepository.saveAndFlush(car);
    }
    @GetMapping("/all-cars")
    public List<Car> allCars(){
        return carRepository.findAll();
    }
    @GetMapping("/{id}")
    public Car carById(@PathVariable Long id){
        Car carFounded = null;
        if(carRepository.existsById(id)){
            carFounded = carRepository.getReferenceById(id);
        }
        return carFounded;
    }
    @PutMapping("/update-type/{id}")
    public Car updateCar(@PathVariable Long id, @RequestParam String type){
        Car carUpdated = carById(id);
        if(carUpdated!=null){
            carUpdated.setType(type);
            carRepository.save(carUpdated);
        }
        return carUpdated;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return ResponseEntity.ok("Auto eliminata con successo");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Auto id " + id + " non trovata");
        }
    }
    @DeleteMapping("/delete-all")
    public void deleteDb(){
        carRepository.deleteAll();
    }
}
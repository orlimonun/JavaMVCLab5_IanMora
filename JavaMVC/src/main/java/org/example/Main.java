package org.example;

import org.example.controller.AuthController;
import org.example.controller.CarController;
import org.example.controller.MantController;
import org.example.models.Car;
import org.example.models.User;
import org.example.models.Mantenimiento;
import org.example.services.AuthService;
import org.example.services.CarService;
import org.example.services.MantService;

import org.example.utilities.HibernateUtil;

import java.util.Date;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CarService carService = new CarService(HibernateUtil.getSessionFactory());
        CarController carController = new CarController(carService);

        AuthService authService = new AuthService(HibernateUtil.getSessionFactory());
        AuthController authController = new AuthController(authService);

        MantService mantService = new MantService(HibernateUtil.getSessionFactory());
        MantController mantController = new MantController(mantService);

        try {
            User user = authController.register("johndoe", "john@example.com", "password123", "USER");

            // Create a car
            Car car = carController.createCar("Toyota", "86", 2022, user);
            System.out.println("Created car: " + car.getMake() + " " + car.getModel());

            // List cars for the user
            carController.getCarsByUser(user).forEach(c ->
                    System.out.println("Car: " + c.getYear() + " " + c.getMake() + " " + c.getModel())
            );

            Mantenimiento mantenimiento1 = mantController.createMantenimiento(1L ,new Date(),"cambio de aceite", "Preventivo",car);


            mantController.getMantenimientoByCar(car).forEach(c ->
                    System.out.println("Mantenimiento: " + c.getId() + " " + c.getDescripcion() + " " + c.getTipo() + " " + c.getFecha()));
        } catch (Exception e) {
            e.printStackTrace();
            //hola
        }
    }
}

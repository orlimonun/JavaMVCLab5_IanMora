package org.example;

import org.example.Server.MessageBroadcaster;
import org.example.Server.SocketServer;
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

        var createUsers = true;

        if (createUsers) {
            try {
                authService.register("user", "email@example.com", "pass", "USER");
                authService.register("otro", "otro@example.com", "pass", "USER");
            } catch (Exception e) {
                System.out.println("Error al registrar usuarios: " + e.getMessage());
            }
        }


        // Server for request/response (API-like)
        int requestPort = 7000;
        SocketServer requestServer = new SocketServer(
                requestPort,
                authController,
                carController,
                mantController);

        // Server for chat/broadcasting (persistent connections)
        int messagePort = 7001;
        MessageBroadcaster messageBroadcaster = new MessageBroadcaster(messagePort, requestServer);

        // Register the broadcaster with the request server so it can broadcast messages
        requestServer.setMessageBroadcaster(messageBroadcaster);

        // Shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutting down servers...");
            requestServer.stop();
            messageBroadcaster.stop();
        }));

        // Start servers
        requestServer.start();
        messageBroadcaster.start();
        System.out.println("Servers started - Requests: " + requestPort + ", Messages: " + messagePort);
    }

}

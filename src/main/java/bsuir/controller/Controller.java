package bsuir.controller;

import bsuir.dao.entity.Event;
import bsuir.dao.entity.Service;
import bsuir.dao.entity.User;
import bsuir.exception.LoginAlreadyExistException;
import bsuir.service.impl.EventService;
import bsuir.service.impl.ServiceService;
import bsuir.service.impl.UserService;

import java.math.BigDecimal;
import java.util.Scanner;

public class Controller {
    private EventService eventService;
    private ServiceService serviceService;
    private UserService userService;
    private Scanner scanner;

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.menu();
    }

    public Controller() {
        this.eventService = new EventService();
        this.serviceService = new ServiceService();
        this.userService = new UserService();
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        String userMenu = "Please choice action\n1 - create event\n2 - get event\n" +
                "3 - get all events\n4 - delete event\n5 - create service\n" +
                "6 - get service\n7 - get all services\n8 - delete service\n" +
                "9 - create user\n10 - get user\n11 - get all users\n" +
                "12 - delete user\n13 - close";
        int answer;
        do {
            try {
                System.out.println(userMenu);
                if (scanner.hasNextInt()) {
                    answer = scanner.nextInt();
                } else {
                    System.out.println("Input valid number");
                    scanner.next();
                    continue;
                }
                switch (answer) {
                    case 1: {
                        eventService.create(enterEvent());
                        break;
                    }
                    case 2: {
                        System.out.println(eventService.get(enterId()));
                        break;
                    }
                    case 3: {
                        System.out.println(eventService.getAll());
                        break;
                    }
                    case 4: {
                        eventService.delete(enterId());
                        break;
                    }
                    case 5: {
                        serviceService.create(enterService());
                        break;
                    }
                    case 6: {
                        System.out.println(serviceService.get(enterId()));
                        break;
                    }
                    case 7: {
                        System.out.println(serviceService.getAll());
                        break;
                    }
                    case 8: {
                        serviceService.delete(enterId());
                        break;
                    }
                    case 9: {
                        userService.create(enterUser());
                        break;
                    }
                    case 10: {
                        System.out.println(userService.get(enterId()));
                        break;
                    }
                    case 11: {
                        System.out.println(userService.getAll());
                        break;
                    }
                    case 12: {
                        userService.delete(enterId());
                        break;
                    }
                    case 13: {
                        System.out.println("out from user menu");
                        return;
                    }
                    default:
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
            }
        } while (true);
    }

    private User enterUser() throws LoginAlreadyExistException {
        String login = "", password = "";
        User user;
        scanner.nextLine();

        System.out.println("Input login");
        if (scanner.hasNextLine()) {
            login = scanner.nextLine();
        }
        System.out.println("Input password");
        if (scanner.hasNextLine()) {
            password = scanner.nextLine();
        }

        if (userService.loginAlreadyExist(login)) {
            scanner.close();
            throw new LoginAlreadyExistException("This login already exist. Try again");
        } else {
            user = new User(login, password);
        }
        return user;
    }

    private Service enterService() {
        String name;
        long phone;
        Service service = new Service();
        scanner.nextLine();

        System.out.println("Input name of service");
        if (scanner.hasNextLine()) {
            name = scanner.nextLine();
            service.setName(name);
        }
        System.out.println("Input phone of service");
        if (scanner.hasNextLong()) {
            phone = scanner.nextLong();
            service.setPhone(phone);
        }
        return service;
    }

    private Event enterEvent() {
        String name;
        BigDecimal cost;
        Event event = new Event();
        scanner.nextLine();

        System.out.println("Input name of event");
        if (scanner.hasNextLine()) {
            name = scanner.nextLine();
            event.setName(name);
        }
        System.out.println("Input cost of event");
        if (scanner.hasNextBigDecimal()) {
            cost = scanner.nextBigDecimal();
            event.setCost(cost);
        }
        System.out.println("Input Id of user");
        if (scanner.hasNextInt()) {
            int userId = scanner.nextInt();
            User user = new User(userId);
            event.setUser(user);
        }
        return event;
    }

    private int enterId() {
        int num;
        do {
            System.out.println("input Id");
            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                break;
            } else {
                System.out.println("You don't input Id");
                scanner.next();
            }
        } while (true);
        return num;
    }
}

package rental.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Car class
class Car {
    private String licensePlate;
    private String model;
    private boolean isAvailable;

    public Car(String licensePlate, String model) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.isAvailable = true;
    }

    public boolean rentCar() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    public void returnCar() {
        isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getDetails() {
        return model + " (" + licensePlate + ") - Available: " + isAvailable;
    }
}

// Customer class
class Customer {
    private String name;
    private String licenseNumber;

    public Customer(String name, String licenseNumber) {
        this.name = name;
        this.licenseNumber = licenseNumber;
    }

    public String getCustomerInfo() {
        return "Customer: " + name + ", License: " + licenseNumber;
    }

    public String getName() {
        return name;
    }
}

// RentalAgency class
class RentalAgency {
    private List<Car> cars;
    private Map<String, Customer> rentedCars; // licensePlate -> Customer

    public RentalAgency() {
        cars = new ArrayList<>();
        rentedCars = new HashMap<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void rentCar(String licensePlate, Customer customer) {
        for (Car car : cars) {
            if (car.getLicensePlate().equals(licensePlate) && car.isAvailable()) {
                car.rentCar();
                rentedCars.put(licensePlate, customer);
                System.out.println("Car " + licensePlate + " rented successfully by " + customer.getName());
                return;
            }
        }
        System.out.println("Car " + licensePlate + " is not available for rent.");
    }

    public void returnCar(String licensePlate) {
        for (Car car : cars) {
            if (car.getLicensePlate().equals(licensePlate) && !car.isAvailable()) {
                car.returnCar();
                Customer customer = rentedCars.remove(licensePlate);
                System.out.println("Car " + licensePlate + " returned successfully by " +
                        (customer != null ? customer.getName() : "unknown customer"));
                return;
            }
        }
        System.out.println("Invalid return request for car " + licensePlate);
    }

    public void showAvailableCars() {
        System.out.println("Available cars:");
        for (Car car : cars) {
            if (car.isAvailable()) {
                System.out.println(car.getDetails());
            }
        }
    }

    public void showRentedCars() {
        System.out.println("Rented cars:");
        for (Map.Entry<String, Customer> entry : rentedCars.entrySet()) {
            System.out.println("Car: " + entry.getKey() + " | Rented by: " + entry.getValue().getCustomerInfo());
        }
    }
}

// Main class to test functionality
public class Main {
    public static void main(String[] args) {
        RentalAgency agency = new RentalAgency();

        Car car1 = new Car("KBA123", "Toyota Corolla");
        Car car2 = new Car("KBB456", "Honda Civic");

        Customer customer1 = new Customer("Alice", "DL12345");
        Customer customer2 = new Customer("Bob", "DL67890");

        agency.addCar(car1);
        agency.addCar(car2);

        agency.showAvailableCars();
        System.out.println();

        agency.rentCar("KBA123", customer1);
        agency.showAvailableCars();
        agency.showRentedCars();
        System.out.println();

        agency.rentCar("KBB456", customer2);
        agency.showAvailableCars();
        agency.showRentedCars();
        System.out.println();

        agency.returnCar("KBA123");
        agency.showAvailableCars();
        agency.showRentedCars();
    }
}

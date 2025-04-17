package com.pluralsight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleInventory {
    ArrayList<Vehicle> inventory;

    public VehicleInventory(Vehicle[] inventory) {
        this.inventory = new ArrayList<Vehicle>();
        this.inventory.addAll(List.of(inventory));


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleInventory inventory = new VehicleInventory(
            new Vehicle[]{
                new Vehicle(101121, "Ford Explorer", "Red", 45000, 13500),
                new Vehicle(101122, "Toyota Camry", "Blue", 60000, 11000),
                new Vehicle(101123, "Chevrolet Malibu", "Black", 50000, 9700),
                new Vehicle(101124, "Honda Civic", "White", 70000, 7500),
                new Vehicle(101125, "Subaru Outback", "Green", 55000, 14500),
                new Vehicle(101126, "Jeep Wrangler", "Yellow", 30000, 16000)
            }
        );
        boolean run = true;
        while(run){
            System.out.print("\n\u001B[1m\u001B[4mWhat do you want to do?\u001B[0m\n\t\u001B[1m\u001B[31m1\u001B[0m - \u001B[0m\u001B[1mList all vehicles\u001B[0m\n\t\u001B[1m\u001B[32m2\u001B[0m - \u001B[0m\u001B[1mSearch by make/model\u001B[0m\n\t\u001B[1m\u001B[33m3\u001B[0m - \u001B[0m\u001B[1mSearch by price range\u001B[0m\n\t\u001B[1m\u001B[34m4\u001B[0m - \u001B[0m\u001B[1mSearch by color\u001B[0m\n\t\u001B[1m\u001B[35m5\u001B[0m - \u001B[0m\u001B[1mAdd a vehicle\u001B[0m\n\t\u001B[1m\u001B[36m6\u001B[0m - \u001B[0m\u001B[1mQuit\u001B[0m\n\u001B[1m\u001B[47m\u001B[30mEnter your command:\u001B[0m ");
            try {switch (scanner.nextInt()){
                case 1:
                    inventory.list_all(scanner);
                    break;
                case 2:
                    inventory.search_make_model(scanner);
                    break;
                case 3:
                    inventory.search_price(scanner);
                    break;
                case 4:
                    inventory.search_color(scanner);
                    break;
                case 5:
                    inventory.add(scanner);
                    break;
                case 6:
                    run = false;
                    break;
                default:
                    System.out.println("\n\n\n\u001B[1m\u001B[33m(Warning) \u001B[47m\u001B[30mInvalid Selection! Please Only Select 1 - 6\u001B[0m\n");
            }} catch (Exception e){
                System.out.println("\n\n\n\u001B[1m\u001B[33m(Warning) \u001B[47m\u001B[30mInvalid Input!\u001B[0m\n");
                scanner.nextLine();
            }
        }
        System.out.println("\u001B[4m\u001B[1m\u001B[31mG\u001B[32mo\u001B[33mo\u001B[34md\u001B[35mb\u001B[36my\u001B[38me\u001B[37m!");
    }
    public void list_all(Scanner scanner){
        System.out.println("\n\u001B[4mCars in Stock:\u001B[0m");
        for (Vehicle vehicle : this.inventory){
            vehicle.print_all();
        }
        System.out.println("\n\u001B[47m\u001B[30m\u001B[1mPress Enter to Continue\u001B[0m");
        scanner.nextLine();
        scanner.nextLine();

    }
    public void search_make_model(Scanner scanner){
        scanner.nextLine();
        System.out.print("\n\u001B[47m\u001B[30m\u001B[1mEnter Make/Model:\u001B[0m ");
        String make_model = scanner.nextLine().trim();
        System.out.println("\n\u001B[4mCars Matching Make/Model:\u001B[0m");
        for (Vehicle vehicle : this.inventory){
            if (vehicle.getMakeModel().equalsIgnoreCase(make_model)) vehicle.print_all();
        }
        System.out.println("\n\u001B[47m\u001B[30m\u001B[1mPress Enter to Continue\u001B[0m");
        scanner.nextLine();
    }
    public void search_price(Scanner scanner){
        System.out.print("\n\u001B[47m\u001B[30m\u001B[1mEnter Min Search Price:\u001B[0m ");
        float min_price = scanner.nextFloat();
        System.out.print("\n\u001B[47m\u001B[30m\u001B[1mEnter Max Search Price:\u001B[0m ");
        float max_price = scanner.nextFloat();
        System.out.println("\n\u001B[4mCars Matching Price Range:\u001B[0m");
        for (Vehicle vehicle : this.inventory){
            float price = vehicle.getPrice();
            if (min_price <= price && price <= max_price) vehicle.print_all();
        }
        System.out.println("\n\u001B[47m\u001B[30m\u001B[1mPress Enter to Continue\u001B[0m");
        scanner.nextLine();
        scanner.nextLine();
    }
    public void search_color(Scanner scanner){
        scanner.nextLine();
        System.out.print("\n\u001B[47m\u001B[30m\u001B[1mEnter Search Color:\u001B[0m ");
        String color = scanner.nextLine().trim();
        System.out.println("\n\u001B[4mCars Matching Color:\u001B[0m");
        for (Vehicle vehicle : this.inventory){
            if (vehicle.getColor().equalsIgnoreCase(color))vehicle.print_all();
        }
        System.out.println("\n\u001B[47m\u001B[30m\u001B[1mPress Enter to Continue\u001B[0m");
        scanner.nextLine();
    }
    public void add(Scanner scanner){
        this.inventory.add(new Vehicle(scanner));
    }
}

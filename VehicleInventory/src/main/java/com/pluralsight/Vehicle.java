package com.pluralsight;

import java.util.Scanner;

public class Vehicle {

    public Vehicle(long vehicleId, String makeModel, String color, int odometerReading, float price) {
        this.vehicleId = vehicleId;
        this.makeModel = makeModel;
        this.color = color;
        this.odometerReading = odometerReading;
        this.price = price;
    }
    public Vehicle(Scanner scanner, VehicleInventory target){
        System.out.println("\n\u001B[4mAdding New Car to Inventory:\u001B[0m");
        System.out.print("\t\u001B[47m\u001B[30m\u001B[1mEnter Vehicle Id:\u001B[0m ");
        this.vehicleId = scanner.nextLong();
        while (target.does_vehicle_id_exist(this.vehicleId)){
            System.out.println("\n\n\n\u001B[1m\u001B[31m(ERROR) \u001B[47m\u001B[30mVehicle ID Already Exists in Stock!\u001B[0m\n");

            System.out.print("\t\u001B[47m\u001B[30m\u001B[1mEnter Vehicle Id:\u001B[0m ");
            this.vehicleId = scanner.nextLong();
        }
        scanner.nextLine();
        System.out.print("\t\u001B[47m\u001B[30m\u001B[1mEnter Make/Model:\u001B[0m ");
        this.makeModel = scanner.nextLine();
        System.out.print("\t\u001B[47m\u001B[30m\u001B[1mEnter Color:\u001B[0m ");
        this.color = scanner.nextLine();
        System.out.print("\t\u001B[47m\u001B[30m\u001B[1mEnter Odometer Reading:\u001B[0m ");
        this.odometerReading = scanner.nextInt();
        while (odometerReading < 0){
            System.out.println("\n\n\n\u001B[1m\u001B[31m(ERROR) \u001B[47m\u001B[30mOdomerter Reading Can't be Negative!\u001B[0m\n");
            System.out.print("\t\u001B[47m\u001B[30m\u001B[1mEnter Odometer Reading:\u001B[0m ");
            this.odometerReading = scanner.nextInt();
        }
        scanner.nextLine();
        System.out.print("\t\u001B[47m\u001B[30m\u001B[1mEnter Price:\u001B[0m ");
        this.price = scanner.nextFloat();
        while(price <= 0){
            System.out.println("\n\n\n\u001B[1m\u001B[31m(ERROR) \u001B[47m\u001B[30mPrice Can't be Less Than 0!\u001B[0m\n");
            System.out.print("\t\u001B[47m\u001B[30m\u001B[1mEnter Price:\u001B[0m ");
            this.price = scanner.nextFloat();
        }
        System.out.print("\n\u001B[1m\u001B[32mCar Has Been Added!\u001B[0m\n");
        this.print_all();
    }
    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    long vehicleId;

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    String makeModel;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    String color;

    public int getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(int odometerReading) {
        this.odometerReading = odometerReading;
    }

    int odometerReading;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    float price;
    public void print_all(){
        System.out.printf("\t- \u001B[4m\u001B[37mVehicle Id:\u001B[0m \u001B[1m%d\u001B[0m, \u001B[4m\u001B[37mMake/Model:\u001B[0m \u001B[1m%s\u001B[0m, \u001B[4m\u001B[37mColor:\u001B[0m \u001B[1m%s\u001B[0m, \u001B[4m\u001B[37mOdometer Reading:\u001B[0m \u001B[1m%d\u001B[0m, \u001B[4m\u001B[37mPrice:\u001B[0m \u001B[1m%.2f\u001B[0m\n", this.vehicleId, this.makeModel, this.color, this.odometerReading, this.price);
    }
}

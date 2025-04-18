package com.pluralsight.vehicle;

import java.util.Scanner;

public class Vehicle {
    //values
    long vehicleId;
    String makeModel;
    String color;
    int odometerReading;
    float price;

    //constructor
    public Vehicle(long vehicleId, String makeModel, String color, int odometerReading, float price) {
        this.vehicleId = vehicleId;
        this.makeModel = makeModel;
        this.color = color;
        this.odometerReading = odometerReading;
        this.price = price;
    }

    //getters
    public long getVehicleId() {
        return vehicleId;
    }
    public String getMakeModel() {
        return makeModel;
    }
    public String getColor() {
        return color;
    }
    public int getOdometerReading() {
        return odometerReading;
    }
    public float getPrice() {
        return price;
    }

    //setters (unused)
    public void setPrice(float price) {
        this.price = price;
    }
    public void setOdometerReading(int odometerReading) {
        this.odometerReading = odometerReading;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }
    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    //print vehicle
    public void print_all(){ //prints all the vehicle's data in a unified format used by other methods
        System.out.printf("\t- \u001B[4m\u001B[37mVehicle Id:\u001B[0m \u001B[1m%d\u001B[0m, \u001B[4m\u001B[37mMake/Model:\u001B[0m \u001B[1m%s\u001B[0m, \u001B[4m\u001B[37mColor:\u001B[0m \u001B[1m%s\u001B[0m, \u001B[4m\u001B[37mOdometer Reading:\u001B[0m \u001B[1m%d\u001B[0m, \u001B[4m\u001B[37mPrice:\u001B[0m \u001B[1m%.2f\u001B[0m\n", this.vehicleId, this.makeModel, this.color, this.odometerReading, this.price);
    }
}

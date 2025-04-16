package com.pluralsight;

import java.util.Scanner;

public class CellPhoneApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CellPhone cell_phone = new CellPhone();
        System.out.print("Serial Number: ");
        cell_phone.set_serialNumber(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Model: ");
        cell_phone.set_model(scanner.nextLine());
        System.out.print("Carrier: ");
        cell_phone.set_carrier(scanner.nextLine());
        System.out.print("Phone Number: ");
        cell_phone.set_phoneNumber(scanner.nextLine());
        System.out.print("Owner: ");
        cell_phone.set_owner(scanner.nextLine());
        System.out.printf("Serial Number: %d\nModel: %s\nCarrier: %s\nPhone Number: %s\nOwner: %s", cell_phone.get_serialNumber(), cell_phone.get_model(), cell_phone.get_carrier(), cell_phone.get_phoneNumber(), cell_phone.get_owner());
    }
}
class CellPhone{
    int serialNumber;
    String model;
    String carrier;
    String phoneNumber;
    String owner;
    public CellPhone(){
        this.serialNumber = 0;
        this.model = "";
        this.carrier = "";
        this.phoneNumber = "";
        this.owner = "";
    }
    public int get_serialNumber(){
        return this.serialNumber;
    }
    public String get_model(){
        return this.model;
    }
    public String get_carrier(){
        return this.carrier;
    }
    public String get_phoneNumber(){
        return this.phoneNumber;
    }
    public String get_owner(){
        return this.owner;
    }
    public void set_serialNumber(int val){
        this.serialNumber = val;
    }
    public void set_model(String val){
        this.model = val;
    }
    public void set_carrier(String val){
        this.carrier = val;
    }
    public void set_phoneNumber(String val){
        this.phoneNumber = val;
    }
    public void set_owner(String val){
        this.owner = val;
    }
}

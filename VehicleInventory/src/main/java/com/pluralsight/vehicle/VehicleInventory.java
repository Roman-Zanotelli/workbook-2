package com.pluralsight.vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleInventory {
    private final ArrayList<Vehicle> inventory; //inventory data
    private final float min_listed_price = 1.00f; //minimum listed price
    public VehicleInventory(){
        this.inventory = new ArrayList<Vehicle>();
        this.inventory.addAll(List.of(new Vehicle[]{ //default values
                new Vehicle(101121, "Ford Explorer", "Red", 45000, 13500),
                new Vehicle(101122, "Toyota Camry", "Blue", 60000, 11000),
                new Vehicle(101123, "Chevrolet Malibu", "Black", 50000, 9700),
                new Vehicle(101124, "Honda Civic", "White", 70000, 7500),
                new Vehicle(101125, "Subaru Outback", "Green", 55000, 14500),
                new Vehicle(101126, "Jeep Wrangler", "Yellow", 30000, 16000)
        }));
    }
    public VehicleInventory(Vehicle[] inventory) { //Initializes Inventory based off list
        this.inventory = new ArrayList<Vehicle>(); //initialize array
        this.inventory.addAll(List.of(inventory)); //copy contents
    }
    public void list_all(){ //print all
        for (Vehicle vehicle : this.inventory){ //cycle through all
            vehicle.print_all();
        }
    }
    public void search_make_model(String make_model){ //search based on make and model
        for (Vehicle vehicle : this.inventory){
            if (vehicle.getMakeModel().equalsIgnoreCase(make_model)) vehicle.print_all(); //print if match
        }
    }
    public void search_price(float min_price, float max_price){ //search all based on price rnage
        for (Vehicle vehicle : this.inventory){
            float price = vehicle.getPrice();
            if (min_price <= price && price <= max_price) vehicle.print_all(); //print if in range
        }
    }
    public void search_color(String color){ //search all based on color
        for (Vehicle vehicle : this.inventory){
            if (vehicle.getColor().equalsIgnoreCase(color)) vehicle.print_all(); //print if match
        }
    }
    public void add(Vehicle vehicle){ //add a vehicle to inv
        this.inventory.add(vehicle); //add to inv
    }
    public boolean does_vehicle_id_exist(long vehicleId){ //checks if a vehicle id exists (used when creating new vehicles)
        for (Vehicle vehicle : this.inventory){
            if (vehicle.getVehicleId() == vehicleId) return true; //print if match
        }
        return false; //if none are found return false
    }

    public void search_id(long id) { //searches for id
        for(Vehicle vehicle : this.inventory){
            if(vehicle.getVehicleId() == id) vehicle.print_all();//print if match
        }
    }

    public void search_miles(int minMiles, int maxMiles) { //searches for mileage range
        for(Vehicle vehicle : this.inventory){
            int mileage = vehicle.getOdometerReading();
            if(minMiles <= mileage && mileage <= maxMiles) vehicle.print_all();//print if in range
        }
    }

    public void search(boolean exclusive, long id, String makeModel, float minPrice, float maxPrice, String color, int minMiles, int maxMiles) { //complex search with all possible parameters, if set to exclusive all valid parameters must match, if false any valid paremeters must match
        for(Vehicle vehicle : this.inventory){ //cycle through all cars in inventory
            if (exclusive){ //all valid parameters must be true
                //defaults value to acceptable range if a valid parameter was not originally present allowing for optional values (-1 or " ")
                //must store it as temp
                long temp_id = id != -1? id : vehicle.getVehicleId();
                String temp_makeModel = !makeModel.isBlank() ? makeModel : vehicle.getMakeModel();
                float temp_minPrice = minPrice != -1 ? minPrice : vehicle.getPrice();
                float temp_maxPrice = maxPrice != -1 ? maxPrice : vehicle.getPrice();
                String temp_color = !color.isBlank() ? color : vehicle.getColor();
                float temp_minMiles = minMiles != -1 ? minMiles : vehicle.getOdometerReading();
                float temp_maxMiles = maxMiles != -1 ? maxMiles : vehicle.getOdometerReading();

                //actual exclusive check
                if (temp_id == vehicle.getVehicleId() && temp_makeModel.equalsIgnoreCase(vehicle.getMakeModel()) && temp_minPrice <= vehicle.getPrice() && temp_maxPrice >= vehicle.getPrice() && temp_color.equalsIgnoreCase(vehicle.getColor()) && temp_minMiles <= vehicle.getOdometerReading() && temp_maxMiles >= vehicle.getOdometerReading()) vehicle.print_all(); //print if valid
            }else {//checks if any valid parameter is true

                //actual inclusive check
                if(id == vehicle.getVehicleId() || makeModel.equalsIgnoreCase(vehicle.getMakeModel()) || (minPrice <= vehicle.getPrice() && maxPrice >= vehicle.getPrice()) || color.equalsIgnoreCase(vehicle.getColor()) || (minMiles <= vehicle.getOdometerReading() && maxMiles >= vehicle.getOdometerReading())) vehicle.print_all(); //print if valid
            }
        }
    }

    public float get_min_listed_price() {
        return min_listed_price;
    }
}

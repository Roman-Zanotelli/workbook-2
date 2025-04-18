package com.pluralsight;

import com.pluralsight.vehicle.Vehicle;
import com.pluralsight.vehicle.VehicleInventory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main { //main logic entry
    static int unexpected_errs = 0; //tracks unexpected errors that have occurred
    static final int unexpected_err_allowance = 3; //total unexpected errors allowed before closing
    static VehicleInventory inventory = new VehicleInventory(); //static shared inventory
    static Scanner scanner; //static scanner used across all modes
    public static void main(String[] args) {
        scanner = new Scanner(System.in); //scanner init
        while(run()){ //main logic entrypoint, run returns false if program should close
            //currently nothing but could be used to give addition debugging/metrics after a complete cycle of user interaction
        }

        DisplayTerminal.Response.GoodBye(); //goodbye!!!
        scanner.close(); //closes the scanner if it was still open
    }


    private static boolean run(){ //main logic entry point
        DisplayTerminal.PromptUser.MainMenu();
        try {
            return UserQuery.MainMenu(); //main menu interaction
        } catch (InputMismatchException e){ //occurs if scanner throws error that isn't properly handled inside function

            DisplayTerminal.WarnUser.InvalidInput.Generic(); //sends generic invalid input warning (attempt within allowance)
            scanner.nextLine(); //clear input
            return true; //continue

        } catch(IllegalStateException e){ //occurs if scanner closes

            DisplayTerminal.ScannerClosedErr(); //sends scanner closed err (do not attempt recover)
            return false; //shuts off program

        } catch (Error e){ //generic unexpected error

            DisplayTerminal.WarnUser.UnexpectedErr(); //sends unexpected warning (attempt recover within allowance)
            scanner.nextLine(); //clear input

            //track allowances for unexpected errors (these shouldn't happen but if they do may be recoverable)
            unexpected_errs++;
            return unexpected_errs > unexpected_err_allowance; //determine shutoff
        }
    }



    //input query logic for user to interact with
    private static class UserQuery {
        public static boolean MainMenu(){ //Entry point for menu logic
            while(!scanner.hasNextInt()){ //check to ensure the input is an int
                DisplayTerminal.WarnUser.InvalidInput.MainMenu(1,6); //print warning and menu again if not
            }
            switch (scanner.nextInt()) { //check input
                case 0:
                    ListAll(); //list all mode
                    break;

                case 1:
                    SearchId(); //search ID mode
                    break;

                case 2:
                    SearchMakeModel(); //search make/model mode
                    break;

                case 3:
                    SearchPrice(); //search price range mode
                    break;

                case 4:
                    SearchColor(); //search color
                    break;

                case 5:
                    SearchOdometer(); //search milage mode
                    break;

                case 6:
                    Search(); //general search mode
                    break;

                case 7:
                    Add(); //add vehicle mode
                    break;

                case 8:
                    return false; //intentionally shut off program

                default:
                    DisplayTerminal.WarnUser.InvalidSelection.Menu(); //warn the user that the selection was not valid (correct input type, wrong value)
                    break;
            }
            return true;
        }
        public static void ListAll(){ //list all cars in inv
            DisplayTerminal.Header.InStock(); //prints header
            inventory.list_all(); //prints all cars

            DisplayTerminal.PromptUser.PressEnterToContinue(); //prompts user to continue
            scanner.nextLine(); // consume carrage return
            scanner.nextLine(); //wait for enter
        }
        public static void SearchColor(){
            DisplayTerminal.Header.SearchingVehicles(); //search header
            scanner.nextLine();

            DisplayTerminal.PromptUser.Color();//prompts user for color
            String color = scanner.nextLine().trim();

            DisplayTerminal.Header.MatchingDescription(); //matching description header
            inventory.search_color(color);

            DisplayTerminal.PromptUser.PressEnterToContinue(); //prompts user to continue
            scanner.nextLine();
        }
        public static void SearchPrice(){
            DisplayTerminal.Header.SearchingVehicles(); //search header

            DisplayTerminal.PromptUser.MinPrice(); //prompt user min price
            float min_price = scanner.nextFloat();

            DisplayTerminal.PromptUser.MaxPrice();//prompt user max price
            float max_price = scanner.nextFloat();

            DisplayTerminal.Header.MatchingDescription();//matching desc header
            inventory.search_price(min_price, max_price); //print matching

            DisplayTerminal.PromptUser.PressEnterToContinue();//prompt to continue
            scanner.nextLine();
            scanner.nextLine();
        }
        public static void SearchMakeModel(){
            DisplayTerminal.Header.SearchingVehicles();//search header
            scanner.nextLine();

            DisplayTerminal.PromptUser.MakeModel(); //prompt user for model
            String make_model = scanner.nextLine().trim();

            DisplayTerminal.Header.MatchingDescription(); //matching desc header
            inventory.search_make_model(make_model); //print matching

            DisplayTerminal.PromptUser.PressEnterToContinue(); //prompt to continue
            scanner.nextLine();
        }
        private static void SearchOdometer() {
            DisplayTerminal.Header.SearchingVehicles();//search header
            DisplayTerminal.PromptUser.MinMiles();//prompt user for min mileage
            int min_miles = scanner.nextInt();

            DisplayTerminal.PromptUser.MaxMiles();//prompt user for max mileage
            int max_miles = scanner.nextInt();

            DisplayTerminal.Header.MatchingDescription(); //matching desc header
            inventory.search_miles(min_miles, max_miles); //print matching

            DisplayTerminal.PromptUser.PressEnterToContinue(); //prompt user to continue
            scanner.nextLine();
            scanner.nextLine();
        }

        private static void Search() {
            DisplayTerminal.WarnUser.Todo(); //warns user this method is still WIP

            DisplayTerminal.Header.SearchingVehicles(); //search header

            DisplayTerminal.PromptUser.ExclusiveSearch(); //prompts user if they want to search exclusively for terms
            boolean exclusive = scanner.nextBoolean();

            DisplayTerminal.PromptUser.ID(); //prompt user for vehicle id
            long id = scanner.nextLong();
            scanner.nextLine();

            DisplayTerminal.PromptUser.MakeModel(); //prompt user for make/model
            String make_model = scanner.nextLine().trim();

            DisplayTerminal.PromptUser.MinPrice(); //prompt user for min price
            float min_price = scanner.nextFloat();

            DisplayTerminal.PromptUser.MaxPrice();//prompt user for max price
            float max_price = scanner.nextFloat();
            scanner.nextLine();

            DisplayTerminal.PromptUser.Color(); //prompt user for color
            String color = scanner.nextLine().trim();

            DisplayTerminal.PromptUser.MinMiles(); //prompt user for min miles
            int min_miles = scanner.nextInt();

            DisplayTerminal.PromptUser.MaxMiles();//prompt user for max miles
            int max_miles = scanner.nextInt();

            inventory.search(exclusive, id, make_model, min_price, max_price, color, min_miles, max_miles); //searches & prints results
        }

        private static void SearchId() {
            DisplayTerminal.Header.SearchingVehicles(); //search header

            DisplayTerminal.PromptUser.ID(); //prompt user for vehicle ID
            long id = scanner.nextLong();

            DisplayTerminal.Header.MatchingDescription();//matching desc header
            inventory.search_id(id); //search and print matching

            DisplayTerminal.PromptUser.PressEnterToContinue(); //prompt user to continue
            scanner.nextLine();
            scanner.nextLine();
        }
        public static void Add(){
            long vehicleId; String makeModel; String color; int odometerReading; float price; //vehicle variables

            DisplayTerminal.Header.AddingVehicle();//display add vehicle header

            DisplayTerminal.PromptUser.ID();//prompt usr for vehicle id
            vehicleId = scanner.nextLong();
            while (inventory.does_vehicle_id_exist(vehicleId)){//check if id exists
                DisplayTerminal.WarnUser.Vehicle.IDTaken(); //warn id is taken
                DisplayTerminal.PromptUser.ID(); //re-prompt
                vehicleId = scanner.nextLong(); //retry
            }
            scanner.nextLine(); //clear carriage


            DisplayTerminal.PromptUser.MakeModel(); //prompt make model
            makeModel = scanner.nextLine();

            DisplayTerminal.PromptUser.Color(); //prompt user for color
            color = scanner.nextLine();

            DisplayTerminal.PromptUser.Odometer(); //prompt user for mileage
            odometerReading = scanner.nextInt();
            while (odometerReading < 0){//checks odometer isn't negative
                DisplayTerminal.WarnUser.Vehicle.OdometerTooLow(); //warns user if too low
                DisplayTerminal.PromptUser.Odometer(); //re-prompt
                odometerReading = scanner.nextInt(); //retry
            }
            scanner.nextLine();//clear carriage return

            DisplayTerminal.PromptUser.Price();//prompt user for price
            price = scanner.nextFloat();
            while(price < inventory.min_listed_price){//check price isn't less than minimum listing price
                DisplayTerminal.WarnUser.Vehicle.PriceTooLow(inventory.min_listed_price);
                DisplayTerminal.PromptUser.Price();//re-prompt
                price = scanner.nextFloat(); //retry
            }
            scanner.nextLine();//clear carriage return
            Vehicle new_vehicle = new Vehicle(vehicleId, makeModel, color, odometerReading, price); //add vehicle
            new_vehicle.print_all(); //print vehicle information
            DisplayTerminal.PromptUser.ConfirmAddVehicle(); //ask to confirm
            if (!scanner.nextBoolean()){
                DisplayTerminal.Response.CarHasBeenCanceled();//acknowledge the cancel
                return; //exit early berfore adding
            }

            inventory.add(new_vehicle); //add vehicle
            DisplayTerminal.Response.CarHasBeenAdded(); //acknowledge successful add
        }
    }

}

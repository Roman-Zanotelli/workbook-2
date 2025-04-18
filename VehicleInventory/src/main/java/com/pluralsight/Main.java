package com.pluralsight;

import com.pluralsight.vehicle.Vehicle;
import com.pluralsight.vehicle.VehicleInventory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main { //main logic entry
    private static int unexpected_errs = 0; //tracks unexpected errors that have occurred
    private static final int unexpected_err_allowance = 3; //total unexpected errors allowed before closing
    private static VehicleInventory inventory = new VehicleInventory(); //static shared inventory
    private static Scanner scanner; //static scanner used across all modes
    public static void main(String[] args) {
        scanner = new Scanner(System.in); //scanner init
        //todo: could add a way to load vehicles on startup from previous session here (would replace default values if previous session exists)


        while(run()){ //main logic entrypoint, run returns false if program should close
            //currently nothing but could be used to give addition debugging/metrics after a complete cycle of user interaction
            //todo: could add a way to calculate the time between run cycles here
        }

        DisplayTerminal.Response.GoodBye(); //goodbye!!!
        scanner.close(); //closes the scanner if it was still open

        //todo: could add a way to save inventory here such as with a json representing vehicle array
    }


    private static boolean run(){ //main logic entry point
        try {

            return UserQuery.MainMenu(); //main menu interaction entry point

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

        private static boolean MainMenu(){ //Entry point for menu logic
            switch (Scan.MainMenu()) {
                case 0:
                    Mode.ListAll(); //list all mode
                    break;

                case 1:
                    Mode.SearchId(); //search ID mode
                    break;

                case 2:
                    Mode.SearchMakeModel(); //search make/model mode
                    break;

                case 3:
                    Mode.SearchPrice(); //search price range mode
                    break;

                case 4:
                    Mode.SearchColor(); //search color
                    break;

                case 5:
                    Mode.SearchOdometer(); //search milage mode
                    break;

                case 6:
                    Mode.Search(); //general search mode
                    break;

                case 7:
                    Mode.Add(); //add vehicle mode
                    break;

                case 8:
                    return false; //intentionally shut off program

                default:
                    DisplayTerminal.WarnUser.InvalidSelection.Menu(); //warn the user that the selection was not valid (correct input type, wrong value)
                    break;
            }
            return true; //continue normally
        }


        //Modes called by main menu
        private static class Mode {
            private static void ListAll() { //list all cars in inv
                DisplayTerminal.Header.InStock(); //prints header

                inventory.list_all(); //prints all cars

                Scan.EnterToContinue(); //enter to continue
            }

            private static void SearchColor() {
                DisplayTerminal.Header.SearchingVehicles(); //search header

                String color = Scan.Color(); //scan for color

                DisplayTerminal.Header.MatchingDescription(); //matching description header

                inventory.search_color(color); //actual search

                Scan.EnterToContinue(); //enter to continue
            }

            private static void SearchPrice() {
                DisplayTerminal.Header.SearchingVehicles(); //search header

                float min_price = Scan.MinPrice(); //Handles scanning min price
                float max_price = Scan.MaxPrice(); //Handles scanning mac price

                DisplayTerminal.Header.MatchingDescription();//matching desc header
                inventory.search_price(min_price, max_price); //print matching

                Scan.EnterToContinue(); //Prompt Enter to continue
            }

            private static void SearchMakeModel() {
                DisplayTerminal.Header.SearchingVehicles();//search header

                String make_model = Scan.MakeModel(); //Scan for MakeModel

                DisplayTerminal.Header.MatchingDescription(); //matching desc header
                inventory.search_make_model(make_model); //print matching

                Scan.EnterToContinue(); //Enter to continue
            }

            private static void SearchOdometer() {
                DisplayTerminal.Header.SearchingVehicles();//search header

                int min_miles = Scan.MinMileage(); //Scan min mileage
                int max_miles = Scan.MaxMileage(); //Scan max mileage

                DisplayTerminal.Header.MatchingDescription(); //matching desc header
                inventory.search_miles(min_miles, max_miles); //print matching

                Scan.EnterToContinue(); //Enter to continue
            }

            private static void Search() {
                DisplayTerminal.WarnUser.Todo(); //warns user this method is still WIP

                DisplayTerminal.Header.SearchingVehicles(); //search header

                boolean exclusive = Scan.ExclusiveSearch(); //scan if they want to do exclusive search

                long vehicle_id = Scan.VehicleID(); //scan vehicle id

                String make_model = Scan.MakeModel(); //scan model

                float min_price = Scan.MinPrice(); //scan min price

                float max_price = Scan.MaxPrice(); //scan max price

                String color = Scan.Color(); //scan color

                int min_miles = Scan.MinMileage(); //scan min mileage

                int max_miles = Scan.MaxMileage(); //scan max mileage

                inventory.search(exclusive, vehicle_id, make_model, min_price, max_price, color, min_miles, max_miles); //searches & prints results

                Scan.EnterToContinue(); //enter to continue
            }

            private static void SearchId() {
                DisplayTerminal.Header.SearchingVehicles(); //search header

                long id = Scan.VehicleID(); //scan for vehicle id

                DisplayTerminal.Header.MatchingDescription();//matching desc header
                inventory.search_id(id); //search and print matching

                Scan.EnterToContinue(); //enter to continue
            }

            private static void Add() {
                //vehicle variables
                long vehicleId;
                String makeModel;
                String color;
                int odometerReading;
                float price;

                DisplayTerminal.Header.AddingVehicle();//display add vehicle header
                vehicleId = Scan.VehicleIDChecked(); //gets vehicle ID checked against inv

                makeModel = Scan.MakeModel(); //gets model

                color = Scan.Color(); //gets color

                odometerReading = Scan.Mileage(); //handles scanning mileage with min bound at 0

                price = Scan.PriceChecked(); //scan price checked against min listing price


                Vehicle new_vehicle = new Vehicle(vehicleId, makeModel, color, odometerReading, price); //add vehicle
                new_vehicle.print_all(); //print vehicle information

                if (Scan.VehicleConfirmation()) { //Get confirmation
                    inventory.add(new_vehicle); //add vehicle
                    DisplayTerminal.Response.CarHasBeenAdded(); //acknowledge successful add
                }
                Scan.EnterToContinue(); //enter to continue
            }
        }

        //Class for prompting and scanning from user
        private static class Scan{
            private static int MainMenu(){
                DisplayTerminal.PromptUser.MainMenu(); //display menu

                while(!scanner.hasNextInt()){ //check to ensure the input is an int
                    DisplayTerminal.WarnUser.InvalidInput.MainMenu(1,6); //print warning and menu again if not
                }


                int res = scanner.nextInt(); //get selection
                scanner.nextLine(); //consume CRLF
                return res; //return res
            }

            private static void EnterToContinue(){
                DisplayTerminal.PromptUser.Vehicle.PressEnterToContinue(); //prompts user to continue

                scanner.nextLine(); //wait for enter
            }

            private static String Color(){
                DisplayTerminal.PromptUser.Vehicle.Color();//prompts user for color

                return scanner.nextLine().trim();
            }

            private static float MinPrice(){//todo: add input check and min listing price
                DisplayTerminal.PromptUser.Vehicle.MinPrice(); //prompt user min price

                float min_price = scanner.nextFloat(); //gets in
                scanner.nextLine(); //consume return
                return min_price; //return res
            }

            private static float MaxPrice(){ //todo: add input check
                DisplayTerminal.PromptUser.Vehicle.MaxPrice();//prompt user max price

                float max_price = scanner.nextFloat(); //gets in
                scanner.nextLine(); //consume return
                return  max_price; //return res
            }

            private static String MakeModel(){
                DisplayTerminal.PromptUser.Vehicle.MakeModel(); //prompt user for model

                return scanner.nextLine().trim();
            }

            private static int MinMileage(){ //todo: add input type and negative check
                DisplayTerminal.PromptUser.Vehicle.MinMiles();//prompt user for min mileage

                int min_miles = scanner.nextInt();
                scanner.nextLine();
                return min_miles;
            }

            private static int MaxMileage(){ //todo: add input check
                DisplayTerminal.PromptUser.Vehicle.MaxMiles();//prompt user for max mileage

                int max_miles = scanner.nextInt();
                scanner.nextLine();
                return max_miles;
            }

            private static long VehicleID(){//todo: add input check
                DisplayTerminal.PromptUser.Vehicle.ID(); //prompt user for vehicle id

                long id = scanner.nextLong();
                scanner.nextLine();
                return id;
            }

            private static long VehicleIDChecked(){//todo: add input check
                DisplayTerminal.PromptUser.Vehicle.ID(); //prompt user for vehicle id

                long id = scanner.nextLong();
                while (inventory.does_vehicle_id_exist(id)){//check if id exists
                    DisplayTerminal.WarnUser.Vehicle.IDTaken(); //warn id is taken
                    DisplayTerminal.PromptUser.Vehicle.ID(); //reprompt user for vehicle id
                    id = scanner.nextLong(); //retry
                }
                scanner.nextLine(); //clear return
                return id;
            }

            private static int Mileage(){//todo: add input check
                DisplayTerminal.PromptUser.Vehicle.Odometer(); //prompt user for mileage

                int mileage = scanner.nextInt();
                while (mileage < 0){//checks odometer isn't negative
                    DisplayTerminal.WarnUser.Vehicle.OdometerTooLow(); //warns user if too low
                    DisplayTerminal.PromptUser.Vehicle.Odometer(); //prompt user for mileage
                    mileage = scanner.nextInt(); //retry
                }
                scanner.nextLine();
                return mileage;
            }

            private static float PriceChecked(){//todo: add input check
                DisplayTerminal.PromptUser.Vehicle.Price();//prompt user for price

                float price = scanner.nextFloat();
                while(price < inventory.get_min_listed_price()){//check price isn't less than minimum listing price
                    DisplayTerminal.WarnUser.Vehicle.PriceTooLow(inventory.get_min_listed_price());
                    DisplayTerminal.PromptUser.Vehicle.Price();//re-prompt
                    price = scanner.nextFloat(); //retry
                }
                scanner.nextLine();//clear carriage return
                return price; //return price
            }

            private static boolean VehicleConfirmation(){ //todo: add invalid input check
                DisplayTerminal.PromptUser.ConfirmAddVehicle(); //promptto confirm

                boolean res = scanner.nextBoolean(); //get in
                if (!res){ //check cancel
                    DisplayTerminal.Response.CarHasBeenCanceled(); //acknowledge the cancel
                }
                scanner.nextLine(); //clear line
                return res; //return res
            }

            private static boolean ExclusiveSearch(){ //todo: add invalid input check
                DisplayTerminal.PromptUser.ExclusiveSearch(); //prompts user if they want to search exclusively for terms

                boolean exclusive = scanner.nextBoolean(); //get res
                scanner.nextLine(); //clear line
                return exclusive; //return res
            }
        }
    }

}

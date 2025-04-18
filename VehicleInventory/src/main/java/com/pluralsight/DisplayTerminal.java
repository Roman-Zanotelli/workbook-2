package com.pluralsight;



//todo: Add a war to impose format settings for overarching classes reducing ANSI escape code redundancy/repetition
public class DisplayTerminal { //Class used to unify/simplify printing menus/text to the terminal for the user to read.

    public static void ScannerClosedErr(){ //err for when the scanner unexpectedly closes
        System.out.println("\n\n\n\u001B[1m\u001B[33mScanner Closed! Exiting program!\u001B[0m");
    }

    //response subclass
    public static class Response{ //responses to display to the user
        public static void GoodBye(){ //goodbye response when program exists
            System.out.println("\u001B[4m\u001B[1m\u001B[31mG\u001B[32mo\u001B[33mo\u001B[34md\u001B[35mb\u001B[36my\u001B[38me\u001B[37m!!!");
        }
        public static void CarHasBeenAdded(){ //confirmation when new vehicle is successfully created and added to inventory
            System.out.print("\n\u001B[1m\u001B[32mCar Has Been Added!\u001B[0m\n");
        }

        public static void CarHasBeenCanceled() {//confirmation when new vehicle is canceled
            System.out.print("\n\u001B[1m\u001B[33mCar Has Been Canceled!\u001B[0m\n");
        }
    }

    //header subclass todo: could add logic to automatically apply header format for a given string instead of manually declaring pattern using "reset underline val reset"
    public static class Header{ //header used to separate modes and information
        public static void AddingVehicle(){ //header for when adding a vehicle
            System.out.println("\n\u001B[4mAdding New Vehicle to Inventory:\u001B[0m");
        }
        public static void MatchingDescription(){ //header for when displaying matching cars to search query
            System.out.println("\n\u001B[4mVehicles Matching Description:\u001B[0m");
        }
        public static void InStock(){ //header for when displaying all vehicles in stock
            System.out.println("\n\u001B[4mVehicles in Stock:\u001B[0m");
        }
        public static void SearchingVehicles(){ //header for when beginning search query for vehicles
            System.out.println("\n\u001B[4mSearching Vehicles:\u001B[0m");
        }
    }

    //prompt user subclass todo: could add logic to automatically apply prompt format for a given string instead of manually declaring pattern similar to header (would likely need to separate the bool prompts due to slightly differing color pattern)
    public static class PromptUser{ //prompts for users to interact with the program
        //Main menu, confirmAddVehicle, Exclusive search slightly differ from standard prompt format, todo:  separating them would be best for organization and implementing automatic format
        public static void MainMenu(){ //main menu prompt with all possible modes and user selection area
            System.out.print("\n\u001B[1m\u001B[4mWhat do you want to do?\u001B[0m\n\t\u001B[1m0\u001B[0m - \u001B[0m\u001B[1mList all vehicles\u001B[0m\n\t\u001B[1m\u001B[31m1\u001B[0m - \u001B[0m\u001B[1mSearch by vehicle id\u001B[0m\n\t\u001B[1m\u001B[32m2\u001B[0m - \u001B[0m\u001B[1mSearch by make/model\u001B[0m\n\t\u001B[1m\u001B[33m3\u001B[0m - \u001B[0m\u001B[1mSearch by price range\u001B[0m\n\t\u001B[1m\u001B[34m4\u001B[0m - \u001B[0m\u001B[1mSearch by color\u001B[0m\n\t\u001B[1m\u001B[35m5\u001B[0m - \u001B[0m\u001B[1mSearch by odometer\u001B[0m\n\t\u001B[1m\u001B[36m6\u001B[0m - \u001B[0m\u001B[1mSearch\u001B[0m\n\t\u001B[1m\u001B[37m7\u001B[0m - \u001B[0m\u001B[1mAdd a vehicle\u001B[0m\n\t\u001B[1m\u001B[30m8\u001B[0m - \u001B[0m\u001B[1mQuit\u001B[0m\n\u001B[1m\u001B[47m\u001B[30mEnter your command:\u001B[0m ");
        }
        public static void ConfirmAddVehicle() {//prompts user if the want to confirm they vehicle they created
            System.out.print("\n\t\u001B[47m\u001B[30m\u001B[1mAdd Vehicle?\u001B[0m\n\t\u001B[47m\u001B[30m(\u001B[32mtrue\u001B[30m/\u001B[31mfalse\u001B[30m):\u001B[0m ");
        }
        public static void ExclusiveSearch() { //prompts user if they when to search exclusively and displays input area
            System.out.print("\n\t\u001B[47m\u001B[30m\u001B[1mSearch Terms Exclusively?\u001B[0m\n\t\u001B[47m\u001B[30m(\u001B[32mtrue\u001B[30m/\u001B[31mfalse\u001B[30m):\u001B[0m ");
        }

        //Vehicle Prompt sub-subclass
        public static class Vehicle { //prompts directly related to vehicles
            public static void Odometer() { //prompt user to enter odometer reading when creating vehicle
                System.out.print("\t\u001B[47m\u001B[30m\u001B[1mEnter Odometer Reading:\u001B[0m ");
            }

            public static void ID() { //prompt user for vehicle id
                System.out.print("\t\u001B[47m\u001B[30m\u001B[1mEnter Vehicle Id:\u001B[0m ");
            }

            public static void Price() { //prompt user for vehicle price
                System.out.print("\t\u001B[47m\u001B[30m\u001B[1mEnter Price:\u001B[0m ");
            }

            public static void Color() { //prompt user for vehicle color
                System.out.print("\t\u001B[47m\u001B[30m\u001B[1mEnter Color:\u001B[0m ");
            }

            public static void MinPrice() { //prompt user for min price when searching
                System.out.print("\t\u001B[47m\u001B[30m\u001B[1mEnter Min Price:\u001B[0m ");
            }

            public static void MaxPrice() { //prompt user for max price when searcing
                System.out.print("\t\u001B[47m\u001B[30m\u001B[1mEnter Max Price:\u001B[0m ");
            }

            public static void MakeModel() {//prompt user for make or model
                System.out.print("\t\u001B[47m\u001B[30m\u001B[1mEnter Make/Model:\u001B[0m ");
            }

            public static void PressEnterToContinue() {//prompts user to press enter to continue
                System.out.println("\n\u001B[47m\u001B[30m\u001B[1mPress Enter to Continue\u001B[0m");
            }

            public static void MaxMiles() {//prompt user for max mileage when searching
                System.out.print("\t\u001B[47m\u001B[30m\u001B[1mEnter Max Milage:\u001B[0m ");
            }

            public static void MinMiles() {//prompt user for min mileage when searching
                System.out.print("\t\u001B[47m\u001B[30m\u001B[1mEnter Min Milage:\u001B[0m ");
            }
        }
    }

    //warning subclass todo: add unified formatting for a set of string similar to other subclasses, warnings should be prefixed with the same warning string and have the same highlight/color
    public static class WarnUser{//warn the user of issues

        public static void Todo(){//warns the user this mode or functionality is still a work in progress a
            System.out.println("\n\n\n\u001B[1m\u001B[33m(Warning) \u001B[47m\u001B[30mThis Function is a WIP (may not work properly)\u001B[0m\n");
        }

        public static void UnexpectedErr(){ //warns the user of an unexpected error (add a way to display current count and allowance)
            System.out.println("\n\n\n\u001B[1m\u001B[33m(Warning) \u001B[47m\u001B[30mUnexpected Error!\u001B[0m\n");
        }

        //vehicle warning sub-subclass
        public static class Vehicle{ //warnings directly related to vehicle creation

            public static void PriceTooLow(float min_listed_price) { //warns price entered is lower than the min listing price
                System.out.printf("\n\n\n\u001B[1m\u001B[33m(Warning) \u001B[47m\u001B[30mPrice Can't be Less Than Minimum Listing Pice ($%.2f)!\u001B[0m\n\n", min_listed_price);
            }

            public static void OdometerTooLow() {//warns user odometer is negative
                System.out.println("\n\n\n\u001B[1m\u001B[33m(Warning) \u001B[47m\u001B[30mOdomerter Reading Can't be Negative!\u001B[0m\n");
            }

            public static void IDTaken() { //warns user vehicle ID is taken
                System.out.println("\n\n\n\u001B[1m\u001B[33m(Warning) \u001B[47m\u001B[30mVehicle ID Already Exists in Stock!\u001B[0m\n");
            }

        }

        //invalid selection warning sub-subclass
        public static class InvalidSelection{ //warnings for invalid selection (type is correct)

            public static void Menu(){ //main menu warning
                System.out.println("\n\n\n\u001B[1m\u001B[33m(Warning) \u001B[47m\u001B[30mInvalid Selection! Please Only Select 1 - 6\u001B[0m\n");
            }

        }


        //invalid input warning sub-subclass todo: could add more detailed warnings and reprompts related to specific mode/issue
        public static class InvalidInput{ //warns of invalid input (wrong type)

            public static void MainMenu(int min, int max){ //warning used in main menu
                System.out.printf("\n\n\n\u001B[1m\u001B[33m(Warning) \u001B[47m\u001B[30mInvalid Input! Main Menu Only Accepts Integers, Please Select %d - %d\u001B[0m\n", min, max);
                PromptUser.MainMenu();
            }

            public static void Generic(){ //generic warning used by try catch
                System.out.println("\n\n\n\u001B[1m\u001B[33m(Warning) \u001B[47m\u001B[30mInvalid Input!\u001B[0m\n");
            }

        }

    }

}

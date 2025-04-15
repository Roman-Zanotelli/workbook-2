package com.pluralsight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TheaterReservations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Prompt the user for their full name, the date of the show, and the number of tickets they will need
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine().trim().replace(" ",", ").replace(",,", ","); //The last replace is just covers the event that they enter their name as First, Last instead of First Last
        LocalDate date;
        while (true){
            System.out.print("What date will you be coming (MM/DD/YYYY): ");
            try{
                date = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                break;
            } catch (Exception e) {
                System.out.println("\n\n\u001B[33m\u001B[1m(WARNING) \u001B[0m\u001B[1mInvalid Format! Date Must Be In MM/DD/YYYY Format!\u001B[0m");
            }
        }
        int ticket_amount;
        while (true){
            System.out.print("How many tickets: ");
            try {
                ticket_amount = scanner.nextInt();
                break;
            } catch (Exception e){
                System.out.println("\n\n\u001B[33m\u001B[1m(WARNING) \u001B[0m\u001B[1mInvalid Format! Please only enter an Integer.\u001B[0m");
                scanner.nextLine();
            }
        }
        System.out.printf("\u001B[1m%d Ticket%s\u001B[0m reserved for \u001B[1m%tF\u001B[0m under \u001B[1m%s", ticket_amount, ticket_amount == 1 ? "" :"s", date, name);
    }
}
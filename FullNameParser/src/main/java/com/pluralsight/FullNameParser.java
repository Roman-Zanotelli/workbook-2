package com.pluralsight;

import java.util.Scanner;

public class FullNameParser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Enter Your Name (Single Space): ");
        String full_name = scanner.nextLine().trim();
        String[] parts = full_name.split(" ");
        System.out.printf("First Name: %s\nMiddle Name: %s\nLast Name: %s", parts[0], parts[1], parts[2]);
    }
}

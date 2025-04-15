package com.pluralsight;

import java.util.Scanner;

public class FullNameApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Enter Your Name\n\tFirst Name: ");
        String first_name = scanner.nextLine().trim() + " ";
        System.out.print("\tMiddle Name: ");
        String middle_name = scanner.nextLine().trim();
        middle_name = middle_name.isBlank() ? "" : middle_name + " ";
        System.out.print("\tLast Name: ");
        String last_name = scanner.nextLine().trim();
        System.out.print("\tSuffix: ");
        String suffix = scanner.nextLine().trim();
        suffix = suffix.isBlank() ? "" : ", " + suffix;
        System.out.printf("%s%s%s%s",first_name,middle_name,last_name,suffix);
    }
}

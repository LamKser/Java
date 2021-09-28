/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author Dinh Hoang Lam
 */
public class Utils {

    public static int getInt(String message) {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        boolean check = true;
        System.out.print(message);
        do {
            try {
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.print("Plese input number: ");
            }
        } while (check);
        return number;
    }

    public static String getString(String message) {
        Scanner sc = new Scanner(System.in);
        String str = null;
        boolean check = true;
        System.out.print(message);
        do {
            str = sc.nextLine();
            if (str.isEmpty()) {
                System.out.print("Please input data: ");
            } else {
                check = false;
            }
        } while (check);
        return str;
    }

    public static LocalDate getDate(String message) {
        String date = null;
        boolean check = true;
        LocalDate ld = null;
        do {
            try {
                date = getString(message);
                ld = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
                check = false;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date!!!");
            }
        } while (check);
        return ld;
    }

    public static boolean getConfirm(String message) {
        String ask;
        boolean check = false;
        do {
            ask = getString(message);
            if (ask.equalsIgnoreCase("Yes")) {
                check = true;
                break;
            } else if (ask.equalsIgnoreCase("No")) {
                check = false;
                break;
            }
        } while (ask != "Yes" && ask != "No");
        return check;
    }
}

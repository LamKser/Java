/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Dinh Hoang Lam
 */
public class Lab1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FoodList food = new FoodList();
        LocalDate expDate = LocalDate.parse("2021-12-12", DateTimeFormatter.ISO_DATE);
        food.add(new Food("666", "Chicken egg", 10.5, "Egg", "Door", expDate.plusMonths(10)));
        food.add(new Food("111", "Fish", 10.2, "Sea food", "Middle", expDate.plusDays(5)));
        food.add(new Food("222", "Orange", 10.6, "cc", "Bottom", expDate.plusDays(3)));
        food.add(new Food("333", "Peach", 10.1, "Fruit", "Door", expDate.plusMonths(6)));
        food.add(new Food("444", "Apple", 10, "Fruit", "Door", expDate.plusYears(4)));

        int choice = 0;
        do {
            System.out.println("Welcome to Food Management - @ 2021 by SE150579 - Dinh Hoang Lam");
            System.out.println("1. Add a new food");
            System.out.println("2. Search a food by name");
            System.out.println("3. Remove the food by ID");
            System.out.println("4. Print the food list in the descending order of expired date");
            System.out.println("5. Save to file");
            System.out.println("6. Read from file");
            System.out.println("7. Quit");
            choice = Utils.getInt("Your choice: ");
            switch (choice) {
                case 1:
                    food.addFood();
                    break;
                case 2:
                    food.searchByName();
                    break;
                case 3:
                    food.removeByID();
                    break;
                case 4:
                    food.printFoodDescending();
                    break;
                case 5:
                    food.saveToFile("food.dat");
                    break;
                case 6:
                    food.readFromFile("food.dat");
                    break;
                case 7:
                    System.out.println("Quit");
                    break;
                default:
                    System.out.println("Enter from 1 to 6");
                    break;
            }
        } while (choice != 7);
    }
}

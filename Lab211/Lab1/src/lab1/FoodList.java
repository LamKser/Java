/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Dinh Hoang Lam
 */
public class FoodList extends ArrayList<Food> {

    public void addFood() {
        do {
            String id = null;
            String name = null;
            double weight = 0;
            String type = null;
            String place = null;
            LocalDate expiredDate = null;

            if (this.isEmpty()) {
                id = Utils.getString("Enter ID: ");
            } else {
                do {
                    id = Utils.getString("Enter ID: ");
                    if (checkID(id)) {
                        System.out.println("Duplicate ID!!!");
                    }
                } while (checkID(id));
            }

            name = Utils.getString("Enter Name of food: ");
            weight = Utils.getDouble("Enter weight (kg): ");
            type = Utils.getString("Enter type of food: ");
            place = Utils.getString("Enter place of food (Door-Top-Middle-Bottom): ");
            expiredDate = Utils.getDate("Enter Expired date (yyyy-mm-dd): ");

            Food food = new Food(id, name, weight, type, place, expiredDate);
            this.add(food);
            System.out.println("Add Successfully!!!");
        } while (Utils.getConfirm("Continue adding (Yes/No)? "));
        System.out.println();
    }

    public void searchByName() {
        if (this.isEmpty()) {
            System.out.println("Food list is empty");
        } else {
            do {
                boolean check = false;
                String key = Utils.getString("Enter key name you want to search: ");
                for (Food obj : this) {
                    if (obj.getName().toLowerCase().contains(key) || obj.getName().toUpperCase().contains(key)) {
                        System.out.println(obj.toString());
                        check = true;
                    }
                }
                if (check) {
                    System.out.println("found");
                } else {
                    System.out.println("Not found");
                }
            } while (Utils.getConfirm("Continue searching (Yes/No)? "));
        }
        System.out.println();
    }

    public void removeByID() {
        boolean check = false;
        String id = null;
        if (this.isEmpty()) {
            System.out.println("Food list is empty");
        } else {
            id = Utils.getString("Enter ID you want to remove: ");
            Food f = this.searchID(id);
            if (f == null) {
                System.out.println("Food ID does not exist");
            } else {
                if (Utils.getConfirm("Do you want to remove (Yes/No)? ")) {
                    this.remove(f);
                    check = true;
                }
            }
        }
        if (check) {
            System.out.println("Remove success!!!");
        } else {
            System.out.println("Removed fail!!!");
        }
    }

    public void printFoodDescending() {
        if (this.isEmpty()) {
            System.out.println("Food List is empty.");
        } else {
            Collections.sort(this, new Comparator<Food>() {
                @Override
                public int compare(Food f1, Food f2) {
                    return f2.getExpiredDate().compareTo(f1.getExpiredDate());
                }
            });

            for (Food obj : this) {
                System.out.println(obj.toString());
            }
        }
    }

    public void saveToFile(String fileName) {
        if (this.isEmpty()) {
            System.out.println("List is Empty");
        } else {
            try {
                FileOutputStream f = new FileOutputStream(fileName);
                ObjectOutputStream fo = new ObjectOutputStream(f);
                for (Food food : this) {
                    fo.writeObject(food);
                }
                fo.close();
                f.close();
                System.out.println("Save file success");
            } catch (Exception e) {
                System.out.println("Save file fail!!!");
            }
        }
    }

    public void readFromFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                FileInputStream fi = new FileInputStream(file);
                ObjectInputStream os = new ObjectInputStream(fi);
                while (fi.available() > 0) {
                    Food food = (Food) os.readObject();
                    System.out.println(food.toString());
                }
                os.close();
                fi.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {

            System.out.println("File is not exist!!!");
        }
    }

    private boolean checkID(String id) {
        boolean check = true;
        Food f = searchID(id);
        if (f == null) {
            check = false;
        }
        return check;
    }

    private boolean checkFileName(String fileName) {
        boolean check = true;
        if (fileName.contains(".dat")) {
            check = false;
        }
        return check;
    }

    private Food searchID(String id) {
        Food food = null;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId().equals(id)) {
                food = this.get(i);
            }
        }
        return food;
    }
}

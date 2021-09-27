/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.io.Serializable;
import java.time.LocalDate;


/**
 *
 * @author Dinh Hoang Lam
 */
public class Food implements Serializable {

    private String id;
    private String name;
    private double weight;
    private String type;
    private String place;
    private LocalDate expiredDate;

    public Food() {
    }

    public Food(String id, String name, double weight, String type, String place, LocalDate expiredDate) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.place = place;
        this.expiredDate = expiredDate;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }
    
    @Override
    public String toString() {
        return "----------------------\n"
                + "ID: " + this.id + "\n"
                + "Name: " + this.name + "\n"
                + "Weight: " + this.weight + "\n"
                + "Type: " + this.type + "\n"
                + "Place: " + this.place + "\n"
                + "Expired date: " + this.expiredDate; 
    }

}

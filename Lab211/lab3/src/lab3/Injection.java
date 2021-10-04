/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;


import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Dinh Hoang Lam
 */
public class Injection implements Serializable{

    private String injectionID;
    private String studentID;
    private String vaccineID;
    private String firstPlace;
    private LocalDate firstDate;
    private String secondPlace;
    private LocalDate secondDate;

    public Injection() {
    }

    public Injection(String injectionID, String studentID, String vaccineID, String firstPlace, LocalDate firstDate, String secondPlace, LocalDate secondDate) {
        this.injectionID = injectionID;
        this.studentID = studentID;
        this.vaccineID = vaccineID;
        this.firstPlace = firstPlace;
        this.firstDate = firstDate;
        this.secondPlace = secondPlace;
        this.secondDate = secondDate;
    }

    public String getInjectionID() {
        return injectionID;
    }

    public void setInjectionID(String injectionID) {
        this.injectionID = injectionID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    
    public String getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(String vaccineID) {
        this.vaccineID = vaccineID;
    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public LocalDate getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(LocalDate firstDate) {
        this.firstDate = firstDate;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    public LocalDate getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(LocalDate secondDate) {
        this.secondDate = secondDate;
    }

    
    @Override
    public String toString() {
        return "-----------------------\n"
                + "Injection ID: " + this.injectionID + "\n"
                + "Student ID: " + this.studentID + "\n"
                + "Vaccine ID: " + this.vaccineID + "\n"
                + "First date: " + this.firstDate + "\n"
                + "First place: " + this.firstPlace + "\n"
                + "Second date: " + this.secondDate + "\n"
                + "Second place: " + this.secondPlace;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;

/**
 *
 * @author Dinh Hoang Lam
 */
public class InjectionList extends ArrayList<Injection> {

    public void showInjected() {
        if (this.isEmpty()) {
            System.out.println("Injection list is empty!!!");
        } else {
            for (Injection injection : this) {
                System.out.println(injection.toString());
            }
        }
    }

    public void addInjection() {
        StudentList studentList = new StudentList();
        VaccineList vaccineList = new VaccineList();

        studentList.addStudentFromFile();
        vaccineList.addVaccineFromFile();
        do {
            String injectionID = null;
            String studentID = null;
            String vaccineID = null;
            String firstPlace = null;
            LocalDate firstDate = null;
            String secondPlace = null;
            LocalDate secondDate = null;

            if (this.isEmpty()) {
                injectionID = Utils.getString("Enter Injection ID: ");
            } else {
                do {
                    injectionID = Utils.getString("Enter Injection ID: ");
                    if (checkInjectionID(injectionID)) {
                        System.out.println("Duplicate Injection ID!!!");
                    }

                } while (checkInjectionID(injectionID));
            }

            studentList.showStudent();
            do {
                studentID = Utils.getString("Enter Student ID: ").toUpperCase();
                if (checkStudentID(studentID)) {
                    System.out.println("Duplicate Student ID!!!");
                }
                if (checkExistStudent(studentID)) {
                    System.out.println("Student ID does not exist!!!");
                }
            } while (checkStudentID(studentID) || checkExistStudent(studentID));

            vaccineList.showVaccine();
            do {
                vaccineID = Utils.getString("Enter vaccine ID: ");
                if (checkExistVaccine(vaccineID)) {
                    System.out.println("Vaccine ID does not exist!!!");
                }
            } while (checkExistVaccine(vaccineID));

            firstPlace = Utils.getString("Enter 1st place: ");

            firstDate = Utils.getDate("Enter 1st date: ");

            if (Utils.getConfirm("Do you want to add 2nd information (Yes/No)? ")) {

                String checkDoseType;
                do {
                    checkDoseType = Utils.getString("Enter 2nd vaccine ID: ");
                    if (!checkDoseType.equals(vaccineID)) {
                        System.out.println("The 1st dose and 2nd dose must have the same type!!!");
                    }
                } while (!checkDoseType.equals(vaccineID));

                secondPlace = Utils.getString("Enter 2nd place: ");

                long checkDate;
                do {
                    secondDate = Utils.getDate("Enter 2nd date: ");
                    checkDate = DAYS.between(firstDate, secondDate);
                    if ((checkDate < 28 || checkDate > 84)) {
                        System.out.println("The 2nd dose must be 4 to 12 weeks");
                    }
                } while ((checkDate < 28 || checkDate > 84));
            }
            Injection injection = new Injection(injectionID, studentID, vaccineID, firstPlace, firstDate, secondPlace, secondDate);
            this.add(injection);
        } while (Utils.getConfirm("Do you want to continue adding (Yes/No)? "));
    }

    public void updateInjection() {
        if (this.isEmpty()) {
            System.out.println("The injection information is empty!!!");
        } else {
            String injectionID = Utils.getString("Enter injection ID you want to update: ");
            Injection injection = searchInjectionID(injectionID);
            if (injection == null) {
                System.out.println("Injection does not exist");
            } else {

                LocalDate newSecondDate = null;
                String newSecondPlace = null;

                long checkDate;
                do {
                    newSecondDate = Utils.getDate("Enter new 2nd date: ");
                    checkDate = DAYS.between(injection.getFirstDate(), newSecondDate);
                    if ((checkDate < 28 || checkDate > 84)) {
                        System.out.println("The 2nd dose must be 4 to 12 weeks");
                    }
                } while ((checkDate < 28 || checkDate > 84));
                newSecondPlace = Utils.getString("Enter new 2nd place: ");
                injection.setSecondDate(newSecondDate);
                injection.setSecondPlace(newSecondPlace);

                System.out.println("--------------After updating--------------");
                System.out.println(injection.toString());
                if ((injection.getFirstDate() != null && injection.getFirstPlace() != null)
                        && (injection.getSecondDate() != null && injection.getSecondPlace() != null)) {
                    System.out.println("Student has completed 2 injections!");
                }
            }
        }
    }

    public void deleteInjection() {
        boolean check = false;
        if (this.isEmpty()) {
            System.out.println("Injection list is empty");
        } else {
            String studentID = Utils.getString("Enter student ID you want to remove: ");
            if (Utils.getConfirm("Do you want to delete (Yes/No)?")) {
                Injection injection = searchStudentID(studentID);
                if (injection == null) {
                    System.out.println("Injection information does not exist!!!");
                } else {
                    this.remove(injection);
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

    public void searchInjection() {
        if (this.isEmpty()) {
            System.out.println("Injection information list is empty");
        } else {
            String studentID = Utils.getString("Enter Student ID you want to search: ");
            Injection inject = this.searchStudentID(studentID);
            if (inject == null) {
                System.out.println("The injection information is not exist!!!");
            } else {
                System.out.println(inject.toString());
            }
        }
    }

    public void saveToFile(String fileName) {
        try {
            FileOutputStream fo = new FileOutputStream(fileName);
            ObjectOutputStream os = new ObjectOutputStream(fo);
            for (Injection injection : this) {
                os.writeObject(injection);
            }
            fo.close();
            os.close();
        } catch (Exception e) {
            System.out.println("Save file fail!!!");
        }
    }

    public void addFromFile(String fileName, boolean check) {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                FileInputStream fi = new FileInputStream(file);
                ObjectInputStream os = new ObjectInputStream(fi);
                while (fi.available() > 0) {
                    Injection injection = (Injection) os.readObject();
                    if (check) {
                        this.add(injection);
                    }
                    else {
                        System.out.println(injection.toString());
                    }
                }
                os.close();
                fi.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private Injection searchStudentID(String studentID) {
        Injection injection = null;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getStudentID().equals(studentID)) {
                injection = this.get(i);
            }
        }
        return injection;
    }

    private boolean checkStudentID(String studentID) {
        boolean check = true;
        Injection stuID = searchStudentID(studentID);
        if (stuID == null) {
            check = false;
        }
        return check;
    }

    private Injection searchInjectionID(String injectionID) {
        Injection injection = null;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getInjectionID().equals(injectionID)) {
                injection = this.get(i);
            }
        }
        return injection;
    }

    private boolean checkInjectionID(String injectionID) {
        boolean check = true;
        Injection injectID = searchInjectionID(injectionID);
        if (injectID == null) {
            check = false;
        }
        return check;
    }

    private boolean checkExistStudent(String studentID) {
        StudentList studentList = new StudentList();
        studentList.addStudentFromFile();
        boolean check = true;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentID().equals(studentID)) {
                check = false;
            }
        }
        return check;
    }

    private boolean checkExistVaccine(String vaccineID) {
        VaccineList vaccineList = new VaccineList();
        vaccineList.addVaccineFromFile();
        boolean check = true;
        for (int i = 0; i < vaccineList.size(); i++) {
            if (vaccineList.get(i).getVaccineID().equals(vaccineID)) {
                check = false;
            }
        }
        return check;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Dinh Hoang Lam
 */
public class VaccineList extends ArrayList<Vaccine> {
    public void addVaccineFromFile() {
        try {
            File file = new File("vaccine.dat");
            if (!file.exists()) {
                System.out.println("File vaccine.dat is not exist");
            }
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String id = stk.nextToken();
                String name = stk.nextToken();
                Vaccine vaccine = new Vaccine(id, name);
                this.add(vaccine);
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void showVaccine() {
        for(Vaccine vaccine : this) {
            System.out.println(vaccine.toString());
        }
    }
}

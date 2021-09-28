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
public class StudentList extends ArrayList<Student> {

    public void addStudentFromFile() {
        try {
            File file = new File("student.dat");
            if (!file.exists()) {
                System.out.println("File student.dat is not exist");
            }
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String id = stk.nextToken();
                String name = stk.nextToken();
                Student student = new Student(id, name);
                this.add(student);
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void showStudent() {
        for(Student student : this) {
            System.out.println(student.toString());
        }
    }
}

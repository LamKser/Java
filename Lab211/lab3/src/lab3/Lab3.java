/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author Dinh Hoang Lam
 */
public class Lab3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        InjectionList list = new InjectionList();
        list.addFromFile("injection.dat", true);
        KeyPair pair = list.getkey();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        int choice;
        do {
            System.out.println("1. Show information all students have been injected");
            System.out.println("2. Add student's vaccine injection information");
            System.out.println("3. Updating information of students vaccine injection");
            System.out.println("4. Delete student vaccine injection information");
            System.out.println("5. Search injection information ");
            System.out.println("6. Show file injection.dat");
            System.out.println("7. Save encrytp injection information");
            System.out.println("8. Read encrption file");
            System.out.println("Others- Quit");
            choice = Utils.getInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    list.showInjected();
                    list.saveToFile("injection.dat");
                    break;
                case 2:
                    list.addInjection();
                    list.saveToFile("injection.dat");
                    break;
                case 3:
                    list.updateInjection();
                    list.saveToFile("injection.dat");
                    break;
                case 4:
                    list.deleteInjection();
                    list.saveToFile("injection.dat");
                    break;
                case 5:
                    list.searchInjection();
                    list.saveToFile("injection.dat");
                    break;
                case 6:
                    list.addFromFile("injection.dat", false);
                    list.saveToFile("injection.dat");
                    break;
                case 7:
                    list.saveEncryption("encrytption information.txt", publicKey);
                    break;
                case 8:
                    list.readEcryption("encrytption information.txt", privateKey);
                    break;
                default:
                    System.out.println("Quit");
                    break;
            }
        } while (choice >= 1 && choice <= 8);
    }

}

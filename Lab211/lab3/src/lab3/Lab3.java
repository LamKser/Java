/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

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
        PrivateKey privateKey = list.readPrivateKey("private.txt");
        PublicKey publicKey = list.readPublicKey("public.txt");
        int choice;
        do {
            System.out.println("1. Show information all students have been injected");
            System.out.println("2. Add student's vaccine injection information");
            System.out.println("3. Updating information of students vaccine injection");
            System.out.println("4. Delete student vaccine injection information");
            System.out.println("5. Search injection information ");
            System.out.println("6. Show injection file");
            System.out.println("7. Encrytp and save injection information");
            System.out.println("8. Read encrption file");
            System.out.println("Others- Quit");
            choice = Utils.getInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    list.showInjected();
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
                    break;
                case 6:
                    list.addFromFile("injection.dat", false);
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
